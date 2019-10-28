package onoffrice.weatherhelp.data.remote.datasources

import onoffrice.weatherhelp.NetworkConstants
import onoffrice.weatherhelp.data.remote.ServiceGenerator
import onoffrice.weatherhelp.data.remote.services.CitiesService

object CitiesDataSource {

    private val mainService =  ServiceGenerator.createService(
            CitiesService::class.java,
            NetworkConstants.LOCALE_URL)

    fun getAllCities(state: String) = mainService.getAllCities(state)
}