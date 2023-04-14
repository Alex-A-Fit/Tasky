package com.alexafit.core.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStorePreferenceStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {
    override val authorizationKey: Flow<String?> =
        dataStore.data.map { it[PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY] }

    override val userNameKey: Flow<String?> =
        dataStore.data.map { it[PreferenceStorage.PreferencesKey.PREF_USER_NAME_KEY] }

    override suspend fun setAuthorizationKey(authorizationKey: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY] = authorizationKey
        }
    }

    override suspend fun clearAuthorizationKey() {
        dataStore.edit { preferences ->
            preferences.remove(PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY)
        }
    }

    override suspend fun setUserName(userName: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceStorage.PreferencesKey.PREF_USER_NAME_KEY] = userName
        }
    }

    override suspend fun clearUserName() {
        dataStore.edit { preferences ->
            preferences.remove(PreferenceStorage.PreferencesKey.PREF_USER_NAME_KEY)
        }
    }
}
