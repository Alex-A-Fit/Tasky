package com.alexafit.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.alexafit.core.BuildConfig
import com.alexafit.core.local.datastore.DataStorePreferenceStorageImpl
import com.alexafit.core.local.datastore.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        val datastore = PreferenceDataStoreFactory.create(
            migrations = listOf(
                SharedPreferencesMigration(context, BuildConfig.DATASTORE_PREFERENCE_NAME)
            ),
            scope = CoroutineScope(Dispatchers.Default)
        ) {
            context.preferencesDataStoreFile(BuildConfig.DATASTORE_PREFERENCE_NAME)
        }
        return datastore
    }

    private val Context.datastore by preferencesDataStore(
        name = BuildConfig.DATASTORE_PREFERENCE_NAME,
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(
                    context,
                    BuildConfig.DATASTORE_PREFERENCE_NAME
                )
            )
        }
    )

    @Singleton
    @Provides
    fun providePreferencesStorage(@ApplicationContext context: Context): PreferenceStorage =
        DataStorePreferenceStorageImpl(context.datastore)
}
