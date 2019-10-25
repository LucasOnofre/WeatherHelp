package onoffrice.weatherhelp.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.data.remote.models.BrState
import onoffrice.weatherhelp.weatherhelp.Constants
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject

class StateCitiesActivity : AppCompatActivity() {

    private val homeViewModel by inject<StateCitiesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel.getCities(intent.getSerializableExtra(Constants.EXTRA_SELECTED_STATE) as String)
        setObservables()
    }

    private fun setObservables() {
        homeViewModel.run {
            isLoading.observe(this@StateCitiesActivity, Observer {

            })

            errorMsg.observe(this@StateCitiesActivity, Observer {

            })

            response.observe(this@StateCitiesActivity, Observer {
                Log.i("RESPOSTA",it.toString())
            })

        }
    }
}


fun Context.createStateCitiesIntent(selectedState: String)
        = intentFor<StateCitiesActivity>(Constants.EXTRA_SELECTED_STATE to selectedState)