package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import onoffrice.weatherhelp.data.remote.datasources.WeatherDataSource
import onoffrice.weatherhelp.data.remote.models.WeatherCitysResponse

interface WeatherRepository {

    fun getCitys(): Single<WeatherCitysResponse>
}

object WeatherRepositoryImplementation: WeatherRepository {
    override fun getCitys(): Single<WeatherCitysResponse> {
        return WeatherDataSource.getCitys()
    }
}
