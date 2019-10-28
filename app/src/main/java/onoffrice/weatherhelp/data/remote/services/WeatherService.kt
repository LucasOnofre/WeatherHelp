package onoffrice.weatherhelp.data.remote.services

import io.reactivex.Single
import onoffrice.weatherhelp.BuildConfig
import onoffrice.weatherhelp.data.remote.models.CityResume
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("city")
    fun getAllCities(@Query("state") state: String,
                     @Query("token") front: String = BuildConfig.MAIN_API_KEY): Single<List<CityResume>>
}