package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.datasources.WeatherDataSource
import onoffrice.weatherhelp.data.remote.models.CityResume

interface WeatherRepository {

    fun getCities(state: String): Single<List<CityResume>>
}

object WeatherRepositoryImplementation: WeatherRepository {
    override fun getCities(state: String): Single<List<CityResume>> {
        return WeatherDataSource.getAllCities(state)
    }
}
