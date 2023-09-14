package com.mobile.mvvm_assignment.hiltmodule

import android.provider.SyncStateContract
import com.mobile.mvvm_assignment.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleTest {

    val BASE_URL = "https://hp-api.onrender.com/api/"
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors()
            .add(HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY))
        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    fun provideCharacterAPI(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}