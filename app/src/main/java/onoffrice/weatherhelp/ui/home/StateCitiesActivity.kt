package onoffrice.weatherhelp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cities.*
import kotlinx.android.synthetic.main.activity_states.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.data.remote.models.CityResume
import onoffrice.weatherhelp.ui.adapter.BrStateCitiesAdapter
import onoffrice.weatherhelp.utils.BaseActivity
import onoffrice.weatherhelp.weatherhelp.Constants
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class StateCitiesActivity : BaseActivity() {

    private lateinit var selectedState: String

    private val adapter by lazy {
        val adapter = BrStateCitiesAdapter(object : BrStateCitiesAdapter.CityClickListener{
            override fun onStateClicked(selectedCity: CityResume) {

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
        setToolbar(getString(R.string.cities_toolbar_title, selectedState))
        setObservables()
        homeViewModel.getCities(selectedState)
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
                adapter.list = it
            })
        }
    }
}

fun Context.createStateCitiesIntent(selectedState: String)
        = intentFor<StateCitiesActivity>(Constants.EXTRA_SELECTED_STATE to selectedState)