package onoffrice.weatherhelp.ui.home

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.remote.models.CityResume
import onoffrice.weatherhelp.data.repositories.WeatherRepository
import onoffrice.weatherhelp.utils.extensions.singleSubscribe

class StateCitiesViewModel (private val weatherRepository: WeatherRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg     = SingleLiveEvent<String>()
    var isLoading    = SingleLiveEvent<Boolean>()
    var response = SingleLiveEvent<List<CityResume>>()

    fun getCities(state: String) {
        isLoading.value = true
        disposable.add(weatherRepository.getCities(state).singleSubscribe(
            onSuccess = {
                isLoading.value = false
                response.value  = it
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