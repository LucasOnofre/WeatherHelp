package onoffrice.weatherhelp.ui.states

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_states.*
import onoffrice.weatherhelp.BaseApplication
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.ui.adapter.BrStatesAdapter
import onoffrice.weatherhelp.utils.BaseActivity
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
        setToolbar(getString(R.string.states_toolbar_title),true)
        setAdapter()
    }

    private fun setAdapter() {
        adapter.list = resources.getStringArray(R.array.all_br_states_list).toMutableList()
    }
}

fun Context.createStatesIntent() = intentFor<StatesActivity>()