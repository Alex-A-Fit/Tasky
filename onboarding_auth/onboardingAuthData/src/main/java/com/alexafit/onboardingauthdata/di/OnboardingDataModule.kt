package com.alexafit.onboardingauthdata.di

import com.alexafit.onboardingauthdata.BuildConfig
import com.alexafit.onboardingauthdata.remote.TaskyApi
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepository
import com.alexafit.onboardingauthdata.repository.OnboardingAuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingDataModule {
    private const val AUTH_HEADER_API_KEY = "x-api-key"
    private const val AUTH_HEADER_CONTENT_TYPE = "Content-Type"
    private const val AUTH_HEADER_CONTENT_TYPE_VALUE = "application/json"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient.addInterceptor(
            Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(AUTH_HEADER_API_KEY, BuildConfig.TASKY_API_KEY)
                    .header(AUTH_HEADER_CONTENT_TYPE, AUTH_HEADER_CONTENT_TYPE_VALUE)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskyApi(client: OkHttpClient): TaskyApi {
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
