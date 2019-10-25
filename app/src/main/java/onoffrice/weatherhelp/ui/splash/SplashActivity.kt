package onoffrice.weatherhelp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.ui.home.createHomeIntent
import onoffrice.weatherhelp.ui.states.createStatesIntent
import onoffrice.weatherhelp.utils.extensions.startActivitySlideTransition

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivitySlideTransition(createStatesIntent())
            finish()
        }, 2000)
    }
}
