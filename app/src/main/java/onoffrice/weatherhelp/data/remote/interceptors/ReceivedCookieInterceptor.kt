package onoffrice.weatherhelp.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import onoffrice.weatherhelp.data.local.PreferencesHelper
import java.io.IOException

private const val RESPONSE_HEADER_COOKIE = "Set-Cookie"

class ReceivedCookieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        val cookie = originalResponse.headers().get(RESPONSE_HEADER_COOKIE)
        try {
            if (!cookie!!.isEmpty() && PreferencesHelper.sessionCookie != cookie) {
                PreferencesHelper.sessionCookie = cookie
            }
        } catch (e: NullPointerException) {
            //Do Nothing
        }

        return originalResponse
    }

}
