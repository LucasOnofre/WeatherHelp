package onoffrice.weatherhelp.ui.cityInfo

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.local.PreferencesHelper
import onoffrice.weatherhelp.data.remote.models.CityInfo
import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.utils.extensions.singleSubscribe

class CityInfoViewModel (private val weatherRepository: WeatherRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg       = SingleLiveEvent<String>()
    var isLoading      = SingleLiveEvent<Boolean>()
    var response       = SingleLiveEvent<CityInfo>()

    fun getCityInfo(city: String, selectedState: String) {
        isLoading.value = true
        disposable.add(weatherRepository.getCityInfo(city, selectedState).singleSubscribe(
            onSuccess = {
                PreferencesHelper.lastCityChecked = it

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

    private fun loadCities() {
        PreferencesHelper.lastCityChecked?.let {
            it.results?.isSaved = true
            it.by = "default"
            response.value = it
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}