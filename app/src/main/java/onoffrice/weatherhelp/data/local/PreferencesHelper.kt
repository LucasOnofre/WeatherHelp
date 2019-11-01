package onoffrice.weatherhelp.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import onoffrice.weatherhelp.data.remote.models.CitiesLocalPersistance
import onoffrice.weatherhelp.data.remote.models.CityInfo
import onoffrice.weatherhelp.data.remote.models.CityResume
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

    private const val PREF_CITIES   = "$SHARED_PREFERENCES_NAME.PREF_CITIES"
    private const val LAST_CITY_CHECKED   = "$SHARED_PREFERENCES_NAME.LAST_CITY_CHECKED"


    fun clearBasicAuth() {
        sharedPreferences.edit().putString(PREF_BASIC_AUTH, "").apply()
    }

    fun clearSharedPref() {
        sharedPreferences.edit().clear().apply()
    }

    var cities: List<CityResume>?
        get() {
            val userJson = sharedPreferences.getString(PREF_CITIES, "")
            val result = Gson().fromJson(userJson, CitiesLocalPersistance::class.java) ?: return listOf()
            return result.cities
        }
        set(value) {
            val banners = CitiesLocalPersistance(value ?: listOf())
            val json = Gson().toJson(banners, CitiesLocalPersistance::class.java)
            sharedPreferences.edit().putString(PREF_CITIES, json).apply()
        }

    var lastCityChecked: CityInfo?
        get() {
            val userJson = sharedPreferences.getString(LAST_CITY_CHECKED, "")
            return Gson().fromJson(userJson, CityInfo::class.java) ?: return CityInfo()
        }
        set(value) {
            val banners = value ?: CityInfo()
            val json = Gson().toJson(banners, CityInfo::class.java)
            sharedPreferences.edit().putString(LAST_CITY_CHECKED, json).apply()
        }

}