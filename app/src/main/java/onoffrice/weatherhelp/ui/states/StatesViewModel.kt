package onoffrice.weatherhelp.ui.states

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import onoffrice.weatherhelp.SingleLiveEvent
import onoffrice.weatherhelp.data.remote.models.CitiesByState

class StatesViewModel () : ViewModel() {

    private val disposable = CompositeDisposable()

    var errorMsg            = SingleLiveEvent<String>()
    var isLoading           = SingleLiveEvent<Boolean>()
    var responseBody        = SingleLiveEvent<Unit>()

    fun handleSelectedState(selectedState: String) {

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}