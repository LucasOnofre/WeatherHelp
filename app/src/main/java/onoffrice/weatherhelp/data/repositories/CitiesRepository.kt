package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.datasources.CitiesDataSource
import onoffrice.weatherhelp.data.remote.models.CityResume

interface CitiesRepository {

    fun getCities(state: String): Single<List<CityResume>>
}

object CitiesRepositoryImplementation: CitiesRepository {
    override fun getCities(state: String): Single<List<CityResume>> {
        return CitiesDataSource.getAllCities(state)
    }
}