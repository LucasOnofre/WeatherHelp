package onoffrice.weatherhelp.data.remote.services

import io.reactivex.Single
import onoffrice.weatherhelp.BuildConfig
import onoffrice.weatherhelp.data.remote.models.BrState
import onoffrice.weatherhelp.data.remote.models.CitiesByState
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("city")
    fun getAllCities(@Query("state") state: String,
                     @Query("token") front: String = BuildConfig.API_KEY): Single<List<CitiesByState>>
}