package onoffrice.weatherhelp.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import onoffrice.weatherhelp.R
import org.jetbrains.anko.intentFor

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}


fun Context.createHomeIntent() = intentFor<HomeActivity>()