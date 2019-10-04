package onoffrice.weatherhelp.data.local

import android.content.Context
import android.content.SharedPreferences
import onoffrice.weatherhelp.weatherhelp.Constants.PACKAGE_NAME

object PreferencesHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private const val SHARED_PREFERENCES_NAME   = "$PACKAGE_NAME.SHARED_PREFERENCES"

    private const val PREF_SESSION_COOKIE       = "$SHARED_PREFERENCES_NAME.PREF_SESSION_COOKIE"
    private const val PREF_USER_ID              = "$SHARED_PREFERENCES_NAME.PREF_USER_ID"
    private const val PREF_BASIC_AUTH           = "$SHARED_PREFERENCES_NAME.PREF_BASIC_AUTH"


    fun clearBasicAuth() {
        sharedPreferences.edit().putString(PREF_BASIC_AUTH, "").apply()
    }

    fun clearSharedPref() {
        sharedPreferences.edit().clear().apply()
    }
}