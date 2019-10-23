package onoffrice.weatherhelp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import onoffrice.weatherhelp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ServiceGenerator {
    companion object {
        fun <S> createService(serviceClass: Class<S>, url: String): S {
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(HttpLoggingInterceptor()
                    .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))

            retrofit.client(httpClient.build())
            return retrofit.build().create(serviceClass)
        }
    }
}