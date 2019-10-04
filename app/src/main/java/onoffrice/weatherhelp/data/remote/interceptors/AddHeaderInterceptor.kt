package onoffrice.weatherhelp.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import onoffrice.weatherhelp.data.local.PreferencesHelper
import java.io.IOException

class AddHeaderInterceptor : Interceptor {
    private val REQUEST_HEADER_BASIC = "Basic "
    private val REQUEST_HEADER_AUTHENTICATION = "Authorization"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val id = PreferencesHelper.basicAuth
        val original = chain.request()
        var request = original

        //Only adds if there is a cookie
        if (!id.isEmpty()) {
            request = original.newBuilder()
                    .addHeader(REQUEST_HEADER_AUTHENTICATION, id)
                    .method(original.method(), original.body())
                    .build()
        }

        return chain.proceed(request)
    }
}