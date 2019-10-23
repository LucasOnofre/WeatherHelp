package onoffrice.weatherhelp.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import onoffrice.weatherhelp.R
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val homeViewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel.getCitys()
        setObservables()
    }

    private fun setObservables() {
        homeViewModel.run {
            isLoading.observe(this@HomeActivity, Observer {

            })

            errorMsg.observe(this@HomeActivity, Observer {

            })

            responseBody.observe(this@HomeActivity, Observer {
                Log.i("RESPOSTA",it.toString())
            })

        }
    }
}


fun Context.createHomeIntent() = intentFor<HomeActivity>()