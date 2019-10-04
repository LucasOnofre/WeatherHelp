package onoffrice.weatherhelp

import androidx.multidex.MultiDexApplication
import onoffrice.weatherhelp.data.KoinInjector
import onoffrice.weatherhelp.data.local.PreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        setPreferencesHelper()
        setKoin()
    }

    private fun setKoin() {
        startKoin {

            androidContext(this@BaseApplication)

            modules(listOf(
                    KoinInjector.splashModule
            ))
        }
    }

    private fun setPreferencesHelper() {
        PreferencesHelper.init(applicationContext)
    }
}
