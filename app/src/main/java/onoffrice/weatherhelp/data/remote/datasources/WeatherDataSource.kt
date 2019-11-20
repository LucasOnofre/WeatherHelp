package onoffrice.weatherhelp.data.remote.datasources

import onoffrice.weatherhelp.NetworkConstants
import onoffrice.weatherhelp.data.remote.ServiceGenerator
import onoffrice.weatherhelp.data.remote.services.WeatherService


object WeatherDataSource {

    private val subService = ServiceGenerator.createService(
        WeatherService::class.java,
        NetworkConstants.WEATHER_URL)

    fun getCityInfo(city: String) = subService.getCityInfo(city = city)
}