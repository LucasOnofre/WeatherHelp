package onoffrice.weatherhelp.ui.home

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.custom_search_component.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.data.remote.models.CityResume
import onoffrice.weatherhelp.ui.adapter.BrStateCitiesAdapter
import onoffrice.weatherhelp.ui.cityInfo.createCityInfoIntent
import onoffrice.weatherhelp.utils.BaseActivity
import onoffrice.weatherhelp.utils.extensions.startActivitySlideTransition
import onoffrice.weatherhelp.weatherhelp.Constants
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.util.*

class StateCitiesActivity : BaseActivity() {

    private lateinit var citiesList: MutableList<CityResume>
    private lateinit var selectedState: String

    private val adapter by lazy {
        val adapter = BrStateCitiesAdapter(object : BrStateCitiesAdapter.CityClickListener{
            override fun onCityClicked(selectedCity: CityResume) {
                homeViewModel.handleCityClicked(selectedCity)
            }
        })
        val layoutManager                = LinearLayoutManager(this)
        citiesRecyclerView.layoutManager = layoutManager
        citiesRecyclerView.adapter       = adapter
        adapter
    }

    private val homeViewModel by inject<StateCitiesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        getExtras()
        setToolbar(getString(R.string.cities_toolbar_title, selectedState), true)
        setObservables()
        setListeners()
        homeViewModel.getCities(selectedState)
    }

    private fun setListeners() {
        setupSearchViewListener()
    }

    private fun getExtras() {
        selectedState = intent.getSerializableExtra(Constants.EXTRA_SELECTED_STATE) as String
    }

    private fun setObservables() {
        homeViewModel.run {
            isLoading.observe(this@StateCitiesActivity, Observer {

            })

            errorMsg.observe(this@StateCitiesActivity, Observer {
                toast(it)
            })

            response.observe(this@StateCitiesActivity, Observer {
                citiesList = it.toMutableList()
                adapter.list = citiesList
            })

            openCityInfo.observe(this@StateCitiesActivity, Observer { selectedCity ->
               openCityInfo(selectedCity)
            })
        }
    }

    private fun setupSearchViewListener() {

        search_src_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {}

            /**
            Set's the filter by any input change
             **/
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text.let {
                    if (it?.length != 0) {
                        val input = text.toString().toLowerCase()
                        filterList(input)
                    }
                }
            }
        })
    }

    /**
    Filter's the cities list and sends to adapter the list filtered
     **/
    private fun filterList(input: String) {
        val filteredList = ArrayList<CityResume>()

        for (city in citiesList) {
            val cityName = city.name.toLowerCase()

            checkTextEquality(cityName, input, filteredList, city)
        }
        adapter.setFilter(filteredList)
    }

    /**
    Check's if the title is equal to the input text
     **/
    private fun checkTextEquality(cityName: String?, input: String, filteredList: MutableList<CityResume>, city: CityResume) {
        if (cityName != null) {
            if (cityName.contains(input)) {
                filteredList.add(city)
            }
        }
    }

    private fun openCityInfo(selectedCity: String) {
        startActivitySlideTransition(createCityInfoIntent(selectedCity, selectedState))
    }
}

fun Context.createStateCitiesIntent(selectedState: String)
        = intentFor<StateCitiesActivity>(Constants.EXTRA_SELECTED_STATE to selectedState)