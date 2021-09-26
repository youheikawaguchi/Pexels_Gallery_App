package com.android.y_kawaguchi.pexels_gallery_app.di

import com.android.y_kawaguchi.pexels_gallery_app.network.ApiGenerator
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun module() = module {
        single {
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single<Converter.Factory> {
            GsonConverterFactory.create(get())
        }

        single {
            OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

        single {
            Retrofit.Builder()
                .addConverterFactory(get())
                .client(get())
        }

        single {
            ApiGenerator(get())
        }
    }
}