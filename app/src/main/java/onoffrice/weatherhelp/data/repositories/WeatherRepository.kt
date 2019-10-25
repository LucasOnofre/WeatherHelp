package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.datasources.WeatherDataSource
import onoffrice.weatherhelp.data.remote.models.CitiesByState

interface WeatherRepository {

    fun getCities(state: String): Single<List<CitiesByState>>
}

object WeatherRepositoryImplementation: WeatherRepository {
    override fun getCities(state: String): Single<List<CitiesByState>> {
        return WeatherDataSource.getAllCities(state)
    }
}
