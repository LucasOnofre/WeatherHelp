package onoffrice.weatherhelp.ui.cityInfo

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.local.PreferencesHelper
import onoffrice.weatherhelp.data.remote.models.CityResume
import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.utils.extensions.singleSubscribe

class CityInfoViewModel (private val weatherRepository: WeatherRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg  = SingleLiveEvent<String>()
    var isLoading = SingleLiveEvent<Boolean>()
    var response  = SingleLiveEvent<List<CityResume>>()

    fun getCityInfo(city: String) {
        isLoading.value = true
        disposable.add(weatherRepository.getCityInfo(city).singleSubscribe(
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