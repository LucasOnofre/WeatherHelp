package onoffrice.weatherhelp.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import onoffrice.weatherhelp.R
import onoffrice.weatherhelp.ui.home.createHomeIntent
import onoffrice.weatherhelp.ui.states.createStatesIntent
import onoffrice.weatherhelp.utils.extensions.fadeInAnimation
import onoffrice.weatherhelp.utils.extensions.startActivitySlideTransition

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setLogoAnimation()
        setDelayForActivity()
    }

    private fun setDelayForActivity() {
        val handle = Handler()
        handle.postDelayed({
            startActivitySlideTransition(createStatesIntent())
            finish()
        }, 4000)
    }

    private fun setLogoAnimation() {
        splash_logo.fadeInAnimation(this)
    }
}
