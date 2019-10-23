package onoffrice.weatherhelp.data.remote.datasources

import onoffrice.weatherhelp.NetworkConstants
import onoffrice.weatherhelp.data.remote.ServiceGenerator
import onoffrice.weatherhelp.data.remote.services.WeatherService


object WeatherDataSource {

    private val service = ServiceGenerator.createService(
        WeatherService::class.java,
        NetworkConstants.BASE_URL)

    fun getCitys() = service.getCitys()
}