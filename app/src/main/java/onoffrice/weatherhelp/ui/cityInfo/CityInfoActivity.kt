package onoffrice.weatherhelp.ui.cityInfo

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.utils.BaseActivity
import onoffrice.weatherhelp.weatherhelp.Constants
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class CityInfoActivity : BaseActivity() {

    private lateinit var selectedCity: String

    private val cityInfoViewModel by inject<CityInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_info)
        getExtras()
        setToolbar(getString(R.string.city_info_toolbar_title, selectedCity))
        setObservables()
        setListeners()
        cityInfoViewModel.getCityInfo(selectedCity)
    }

    private fun getExtras() {
        selectedCity = intent.getSerializableExtra(Constants.EXTRA_SELECTED_CITY) as String
    }

    private fun setListeners() {
    }

    private fun setObservables() {
        cityInfoViewModel.run {
            isLoading.observe(this@CityInfoActivity, Observer {

            })

            errorMsg.observe(this@CityInfoActivity, Observer {
                toast(it)
            })

            response.observe(this@CityInfoActivity, Observer {

            })
        }
    }
}

fun Context.createCityInfoIntent(selectedCity: String)
        = intentFor<CityInfoActivity>(Constants.EXTRA_SELECTED_CITY to selectedCity)