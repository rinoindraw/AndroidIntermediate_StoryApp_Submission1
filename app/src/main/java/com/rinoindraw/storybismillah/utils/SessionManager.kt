package com.rinoindraw.storybismillah.utils

import android.content.Context
import android.content.SharedPreferences
import com.rinoindraw.storybismillah.utils.ConstVal.KEY_EMAIL
import com.rinoindraw.storybismillah.utils.ConstVal.KEY_IS_LOGIN
import com.rinoindraw.storybismillah.utils.ConstVal.KEY_TOKEN
import com.rinoindraw.storybismillah.utils.ConstVal.KEY_USER_ID
import com.rinoindraw.storybismillah.utils.ConstVal.KEY_USER_NAME
import com.rinoindraw.storybismillah.utils.ConstVal.PREFS_NAME

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun setStringPreference(prefKey: String, value: String) {
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun setIntPreference(prefKey: String, value: Int) {
        editor.putInt(prefKey, value)
        editor.apply()
    }

    fun setBooleanPreference(prefKey: String, value: Boolean) {
        editor.putBoolean(prefKey, value)
        editor.apply()
    }

    fun clearPreferenceByKey(prefKey: String) {
        editor.remove(prefKey)
        editor.apply()
    }

    fun clearPreferences() {
        editor.clear().apply()
    }

    val getLimit = prefs.getInt("KEY_LIMIT", 30)
    val getToken = prefs.getString(KEY_TOKEN, "")
    val getUserId = prefs.getString(KEY_USER_ID, "")
    val isLogin = prefs.getBoolean(KEY_IS_LOGIN, false)
    val getUserName = prefs.getString(KEY_USER_NAME, "")
    val getEmail = prefs.getString(KEY_EMAIL, "")
}