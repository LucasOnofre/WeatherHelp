package onoffrice.weatherhelp.data.remote.services

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface WeatherService {

    @GET("listaCidades")
    fun getCitys(): Single<ResponseBody>
}