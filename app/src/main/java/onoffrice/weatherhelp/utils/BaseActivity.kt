package onoffrice.weatherhelp.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import onoffrice.weatherhelp.R


abstract class BaseActivity : AppCompatActivity() {

    //TOOLBAR METHODS
    fun setToolbar(title: String, displayHomeAsUpEnabled: Boolean? = false) {
        setToolbar(title)
        displayHomeAsUpEnabled?.let { supportActionBar!!.setDisplayHomeAsUpEnabled(it) }
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

    }

    //ACTION BAR METHODS
    private fun setToolbar(title: String) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle(title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //CHECK CONNECTION
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }
}
