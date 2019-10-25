package onoffrice.weatherhelp.ui.states

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent

class StatesViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    var openStateCities = SingleLiveEvent<String>()

    fun handleSelectedState(selectedState: String?) {
        openStateCities.value = selectedState ?: "SP"
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}