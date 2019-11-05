package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.datasources.WeatherDataSource
import onoffrice.weatherhelp.data.remote.models.CityInfo


interface WeatherRepository {
    fun getCityInfo(city: String, selectedState: String): Single<CityInfo>
}

object WeatherRepositoryImplementation: WeatherRepository {
    override fun getCityInfo(city: String, selectedState: String): Single<CityInfo> {
        return WeatherDataSource.getCityInfo(
            "$city,${(selectedState.toUpperCase())}"
        )
    }
}
