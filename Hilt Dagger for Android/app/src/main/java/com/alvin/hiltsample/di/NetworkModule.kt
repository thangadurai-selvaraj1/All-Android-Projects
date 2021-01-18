package com.alvin.hiltsample.di

import android.content.Context
import com.alvin.hiltsample.local.dataStore.DataStore
import com.alvin.hiltsample.network.ApiService
import com.alvin.hiltsample.network.AuthInterceptor
import com.alvin.hiltsample.network.constants.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesBaseUrl(): String {
        return ApiConstants.BASE_URL
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesAuthInterceptor(dataStore: DataStore, context: Context): AuthInterceptor {
        return AuthInterceptor(dataStore, context)
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.apply {
            addInterceptor(authInterceptor)
            addInterceptor(httpLoggingInterceptor)
            callTimeout(ApiConstants.SIXTY_SECONDS, TimeUnit.SECONDS)
            connectTimeout(ApiConstants.SIXTY_SECONDS, TimeUnit.SECONDS)
            readTimeout(ApiConstants.SIXTY_SECONDS, TimeUnit.SECONDS)
            writeTimeout(ApiConstants.SIXTY_SECONDS, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        baseUrl: String,
        converter: Converter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}