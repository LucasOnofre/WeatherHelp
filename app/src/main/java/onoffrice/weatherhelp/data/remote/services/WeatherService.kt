package onoffrice.weatherhelp.data.remote.services

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.models.WeatherCitysResponse
import retrofit2.http.GET

interface WeatherService {

    @GET("listaCidades")
    fun getCitys(): Single<WeatherCitysResponse>
}