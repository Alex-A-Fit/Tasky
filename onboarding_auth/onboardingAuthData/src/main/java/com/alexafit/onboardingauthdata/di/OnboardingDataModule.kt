package com.alexafit.onboardingauthdata.di

import com.alexafit.onboardingauthdata.remote.TaskyApi
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): TaskyApi {
        return Retrofit.Builder()
            .baseUrl(TaskyApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideOnboardingAuthRepository(
        api: TaskyApi
    ): OnboardingAuthRepository {
        return OnboardingAuthRepositoryImpl(
            taskyApi = api
        )
    }
}
