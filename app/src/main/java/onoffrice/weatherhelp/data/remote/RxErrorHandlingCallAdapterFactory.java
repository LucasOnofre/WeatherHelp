package onoffrice.weatherhelp.data.remote;
import com.google.gson.JsonParseException;

import onoffrice.weatherhelp.BuildConfig;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private static final String ERROR_UNKNOWN_HOST = "Rede Indisponível";
    private static final String ERROR_TIMEOUT_ERROR = "Tempo Excedido.";
    private static final String ERROR_PARSE_PRODUCTION = "Pode ser necessário atualizar seu aplicativo.";
    private static final String ERROR_PARSE_DEBUG = "JsonParseException";

    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<Type, Object> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Type, Object> {
        private final Retrofit retrofit;
        private final CallAdapter<?, ?> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?, ?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object adapt(Call call) {
            return ((Single) wrapped.adapt(call)).onErrorResumeNext(new Function<Throwable, SingleSource>() {
                @Override
                public SingleSource apply(Throwable throwable) throws Exception {
                    return Single.error(asRetrofitException(throwable));
                }
            });
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            if (throwable instanceof HttpException) {
                // We had non-2XX http error

                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);

            } else if (throwable instanceof IOException) {
                RetrofitException returnableThrowable = RetrofitException.unexpectedError(throwable);
                // A network or conversion error happened
                if (throwable instanceof UnknownHostException) {
                    // Network error
                    throwable = new UnknownHostException(ERROR_UNKNOWN_HOST);
                    returnableThrowable = RetrofitException.networkError((IOException) throwable);
                } else if (throwable instanceof SocketTimeoutException) {
                    // Timeout error
                    throwable = new SocketTimeoutException(ERROR_TIMEOUT_ERROR);
                    returnableThrowable = RetrofitException.networkError((IOException) throwable);
                }
                return returnableThrowable;
            } else if (throwable instanceof JsonParseException) {
                //Parse error
                throwable = new IOException(BuildConfig.DEBUG ?
                        ERROR_PARSE_DEBUG + " " + throwable.getMessage() :
                        ERROR_PARSE_PRODUCTION);
                return RetrofitException.unexpectedError(throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable);
        }
    }
}
