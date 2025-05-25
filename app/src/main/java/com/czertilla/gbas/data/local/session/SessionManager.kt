package com.czertilla.gbas.data.local.session

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SessionManager @Inject constructor(
    context: Context
) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "user_session", Context.MODE_PRIVATE
    )
    private val key = "user_id"

    var userId: String?
        get() = prefs.getString(key, null)
        set(value) = prefs.edit { putString(key, value) }

    fun isLoggedIn(): Boolean = userId != null
    fun logout() = prefs.edit { remove(key) }
}
