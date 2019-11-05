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
import java.text.SimpleDateFormat
import java.util.*



class CityInfoActivity : BaseActivity() {

    private lateinit var selectedCity: String
    private lateinit var selectedState: String

    private val cityInfoViewModel by inject<CityInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_info)
        getExtras()
        setObservables()
        setListeners()
        cityInfoViewModel.getCityInfo(selectedCity, selectedState)
    }

    private fun getExtras() {
        selectedCity  = intent.getSerializableExtra(Constants.EXTRA_SELECTED_CITY) as String
        selectedState = intent.getSerializableExtra(Constants.EXTRA_SELECTED_STATE) as String
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

        if (cityInfo?.by != "default" || cityInfo?.results?.isSaved == false) {
            setToolbar(getString(R.string.city_info_toolbar_title, selectedCity), true)

            cityName.text = cityInfo?.results?.city_name ?: selectedCity
            date.text = cityInfo?.results?.date ?: "11/10/2019"
            timeAccess.text = cityInfo?.results?.time ?: "21:00"
            dayStatus.text = cityInfo?.results?.currently?.toUpperCase() ?: "Noite"
            description.text = cityInfo?.results?.description ?: "Tempo nublado"
            temperature.text = "${cityInfo?.results?.temp.toString()}°C" ?: "25°C"
            humidityValue.text = "${cityInfo?.results?.humidity.toString()}°%"
            windSpeedyValue.text = "${cityInfo?.results?.humidity.toString()}°KM/H"
            sunsetValue.text = cityInfo?.results?.sunset ?: "21:00"
            sunriseValue.text = cityInfo?.results?.sunrise ?: "21:00"

        } else {
            setToolbar(getString(R.string.city_info_toolbar_title, selectedCity), true)

            cityName.text = selectedCity
            date.text = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
            timeAccess.text = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date());
            dayStatus.text = "Noite"
            description.text = "Tempo nublado"
            temperature.text = "25°C"
            humidityValue.text = "20%"
            windSpeedyValue.text = "10°KM/H"
            sunsetValue.text = "18:00"
            sunriseValue.text = "06:00"
        }
    }
}

fun Context.createCityInfoIntent(selectedCity: String, selectedState: String)
        = intentFor<CityInfoActivity>(Constants.EXTRA_SELECTED_CITY to selectedCity,
                                                Constants.EXTRA_SELECTED_STATE to selectedState)