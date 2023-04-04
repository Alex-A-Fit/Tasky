package com.alexafit.core.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import com.alexafit.core.BuildConfig
import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    suspend fun setAuthorizationKey(authorizationKey: String?)
    val authorizationKey: Flow<String>

    object PreferencesKey {
        val PREF_AUTHORIZATION_KEY = stringPreferencesKey(BuildConfig.DATASTORE_AUTH_KEY)
    }
}
