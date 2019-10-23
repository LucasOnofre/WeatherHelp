package onoffrice.weatherhelp.data.repositories

import io.reactivex.Single
import okhttp3.ResponseBody
import onoffrice.weatherhelp.data.remote.datasources.WeatherDataSource

interface WeatherRepository {

    fun getCitys(): Single<ResponseBody>
}

object WeatherRepositoryImplementation: WeatherRepository {
    override fun getCitys(): Single<ResponseBody> {
        return WeatherDataSource.getCitys()
    }
}
