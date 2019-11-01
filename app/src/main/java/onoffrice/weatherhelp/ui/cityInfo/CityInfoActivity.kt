package onoffrice.weatherhelp.ui.cityInfo

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_city_info.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.data.remote.models.CityInfo
import onoffrice.weatherhelp.utils.BaseActivity
import onoffrice.weatherhelp.utils.extensions.setVisible
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
                progressLayout.setVisible(it)
                cityInfoLayout.setVisible(!it)
            })

            errorMsg.observe(this@CityInfoActivity, Observer {
                toast(it)
            })

            response.observe(this@CityInfoActivity, Observer {
                setViews(it)
            })
        }
    }

    private fun setViews(cityInfo: CityInfo?) {
        cityName.text = cityInfo?.results?.city_name ?: "São Paulo"
        date.text = cityInfo?.results?.date ?: "11/10/2019"
        timeAccess.text = cityInfo?.results?.time ?: "21:00"
        dayStatus.text = cityInfo?.results?.currently?.toUpperCase() ?: "Noite"
        description.text = cityInfo?.results?.description ?: "Tempo nublado"
        temperature.text = "${cityInfo?.results?.temp.toString()}°C"
        humidityValue.text = "${cityInfo?.results?.humidity.toString()}°%"
        windSpeedyValue.text = "${cityInfo?.results?.humidity.toString()}°KM/H"
        sunsetValue.text = cityInfo?.results?.sunset ?: "21:00"
        sunriseValue.text = cityInfo?.results?.sunrise ?: "21:00"
    }
}

fun Context.createCityInfoIntent(selectedCity: String)
        = intentFor<CityInfoActivity>(Constants.EXTRA_SELECTED_CITY to selectedCity)