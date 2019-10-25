package onoffrice.weatherhelp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.weatherhelp.Constants
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
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
                toast(it)
            })

            response.observe(this@StateCitiesActivity, Observer {

            })
        }
    }
}

fun Context.createStateCitiesIntent(selectedState: String)
        = intentFor<StateCitiesActivity>(Constants.EXTRA_SELECTED_STATE to selectedState)