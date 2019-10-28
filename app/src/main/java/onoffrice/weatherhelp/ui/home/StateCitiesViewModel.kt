package onoffrice.weatherhelp.ui.home

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.local.PreferencesHelper
import onoffrice.weatherhelp.data.remote.models.CityResume
import onoffrice.weatherhelp.data.repositories.CitiesRepository
import onoffrice.weatherhelp.utils.extensions.singleSubscribe

class StateCitiesViewModel (private val citiesRepository: CitiesRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg     = SingleLiveEvent<String>()
    var isLoading    = SingleLiveEvent<Boolean>()
    var response     = SingleLiveEvent<List<CityResume>>()
    var openCityInfo = SingleLiveEvent<String>()

    fun getCities(state: String) {
        isLoading.value = true
        disposable.add(citiesRepository.getCities(state).singleSubscribe(
            onSuccess = {

                PreferencesHelper.cities = it

                isLoading.value = false
                response.value  = it
            },
            onError = {
                isLoading.value = false
                errorMsg.value  = it.message

                //Used to get last saved response
                loadCities()
            }
        ))
    }

    fun handleCityClicked(city: CityResume) {
        openCityInfo.value = city.name
    }

    private fun loadCities() {
        PreferencesHelper.cities?.let {
            if (it.isNotEmpty()) {
                response.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}