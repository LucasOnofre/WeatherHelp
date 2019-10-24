package onoffrice.weatherhelp.ui.home

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.remote.models.CitiesByState
import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.utils.extensions.singleSubscribe

class HomeViewModel (private val weatherRepository: WeatherRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg            = SingleLiveEvent<String>()
    var isLoading           = SingleLiveEvent<Boolean>()
    var responseBody        = SingleLiveEvent<List<CitiesByState>>()

    fun getCitys() {
        isLoading.value = true
        disposable.add(weatherRepository.getCities().singleSubscribe(
            onSuccess = {
                isLoading.value    = false
                responseBody.value = it
            },
            onError = {
                isLoading.value = false
                errorMsg.value  = it.message
            }
        ))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}