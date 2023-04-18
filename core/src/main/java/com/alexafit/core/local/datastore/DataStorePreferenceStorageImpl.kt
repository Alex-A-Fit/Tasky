package com.alexafit.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

    private val DATA_STORE_AUTHORIZATION_TOKEN_KEY = stringPreferencesKey("pref_auth_key")
    private val DATA_STORE_USER_NAME_KEY = stringPreferencesKey("pref_name_key")
@Singleton
class DataStorePreferenceStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {
    override val authorizationKey: Flow<String?> =
        dataStore.data.map { it[DATA_STORE_AUTHORIZATION_TOKEN_KEY] }

    override val userNameKey: Flow<String?> =
        dataStore.data.map { it[DATA_STORE_USER_NAME_KEY] }

    override suspend fun setAuthorizationKey(authorizationKey: String) {
        dataStore.edit { preferences ->
            preferences[DATA_STORE_AUTHORIZATION_TOKEN_KEY] = authorizationKey
        }
    }

    override suspend fun clearAuthorizationKey() {
        dataStore.edit { preferences ->
            preferences.remove(DATA_STORE_AUTHORIZATION_TOKEN_KEY)
        }
    }

    override suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[DATA_STORE_USER_NAME_KEY] = userName
        }
    }

    override suspend fun clearUserName() {
        dataStore.edit { preferences ->
            preferences.remove(DATA_STORE_USER_NAME_KEY)
        }
    }
}
