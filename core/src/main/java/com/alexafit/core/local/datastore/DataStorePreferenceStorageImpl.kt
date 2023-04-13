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

    override suspend fun setAuthorizationKey(authorizationKey: String) {
        dataStore.edit { settings ->
            settings[PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY] = authorizationKey
        }
    }

    override suspend fun clearAuthorizationKey() {
        dataStore.edit { settings ->
            settings.remove(PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY)
        }
    }

    override val authorizationKey: Flow<String?> =
        dataStore.data.map { it[PreferenceStorage.PreferencesKey.PREF_AUTHORIZATION_KEY] }
}
