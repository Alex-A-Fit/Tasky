package com.alexafit.core.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey
import com.alexafit.core.BuildConfig
import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    suspend fun setAuthorizationKey(authorizationKey: String)
    suspend fun clearAuthorizationKey()
    val authorizationKey: Flow<String?>

    suspend fun setUserName(userName: String)
    suspend fun clearUserName()
    val userNameKey: Flow<String?>

    object PreferencesKey {
        val PREF_AUTHORIZATION_KEY = stringPreferencesKey(BuildConfig.DATASTORE_AUTH_KEY)
        val PREF_USER_NAME_KEY = stringPreferencesKey(BuildConfig.DATASTORE_NAME_KEY)
    }
}
