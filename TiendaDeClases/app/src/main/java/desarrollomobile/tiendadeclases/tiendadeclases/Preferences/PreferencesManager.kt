package desarrollomobile.tiendadeclases.tiendadeclases.Preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager {

    lateinit var preferences: SharedPreferences

    constructor(activity: Activity) {
        this.preferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    }

    fun getStringPreference(preference: String): String {
        val string = preferences.getString(preference, "")
        if (string != null) {
            return string
        } else {
            return ""
        }
    }

    fun getBooleanPreference(preference: String): Boolean {
        return preferences.getBoolean(preference, true)
    }

    fun setStringPreference(preference: String, value: String) {
        val editor = preferences.edit()
        editor.putString(preference, value)
        editor.apply()
    }

    fun setBooleanPreference(preference: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(preference, value)
        editor.apply()
    }


}