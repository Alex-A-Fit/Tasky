package com.alexafit.core.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    suspend fun setAuthorizationKey(authorizationKey: String)
    suspend fun clearAuthorizationKey()
    val authorizationKey: Flow<String?>

    suspend fun setUserName(userName: String)
    suspend fun clearUserName()
    val userNameKey: Flow<String?>
}
