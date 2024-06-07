package com.example.tugas_advance.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKey {
    const val NAME_PREF = "login_preferences"
    const val NAME_KEY = "name"
    const val EMAIL_KEY = "email"
    const val PASSWORD_KEY = "password"

    val STATUS_LOGIN_KEY = booleanPreferencesKey("StatusLogin")
}