package onoffrice.weatherhelp.data.remote.services

import io.reactivex.Single
import onoffrice.weatherhelp.BuildConfig
import onoffrice.weatherhelp.data.remote.models.CityInfo
import onoffrice.weatherhelp.data.remote.models.CityResume
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCityInfo(@Query("key") key: String = BuildConfig.SUB_API_KEY,
                    @Query("city_name") city: String): Single<CityInfo>
}

