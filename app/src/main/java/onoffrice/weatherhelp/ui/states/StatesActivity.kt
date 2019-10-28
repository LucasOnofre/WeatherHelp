package onoffrice.weatherhelp.ui.states

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_states.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.ui.adapter.BrStatesAdapter
import onoffrice.weatherhelp.ui.home.createStateCitiesIntent
import onoffrice.weatherhelp.utils.BaseActivity
import onoffrice.weatherhelp.utils.extensions.startActivitySlideTransition
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject

class StatesActivity : BaseActivity() {

    private val stateViewModel by inject<StatesViewModel>()

    private val adapter by lazy {
        val adapter = BrStatesAdapter(object : BrStatesAdapter.StateClickListener {
            override fun onStateClicked(selectedState: String) {
                stateViewModel.handleSelectedState(selectedState)
            }
        })
        val layoutManager                = LinearLayoutManager(this)
        statesRecyclerView.layoutManager = layoutManager
        statesRecyclerView.adapter       = adapter
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_states)
        setToolbar(getString(R.string.states_toolbar_title))
        setAdapter()
        setObservers()
    }

    private fun setObservers() {
        stateViewModel.run {
            openStateCities.observe(this@StatesActivity, Observer { selectedState ->
                openStateCities(selectedState)
            })
        }
    }

    private fun setAdapter() {
        adapter.list = resources.getStringArray(R.array.all_br_states_list).toMutableList()
    }

    private fun openStateCities(selectedState: String) {
        startActivitySlideTransition(
           createStateCitiesIntent(selectedState)
        )
    }
}

fun Context.createStatesIntent() = intentFor<StatesActivity>()