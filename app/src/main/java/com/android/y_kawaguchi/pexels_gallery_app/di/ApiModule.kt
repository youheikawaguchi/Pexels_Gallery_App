package com.android.y_kawaguchi.pexels_gallery_app.di

import com.android.y_kawaguchi.pexels_gallery_app.network.ApiGenerator
import com.android.y_kawaguchi.pexels_gallery_app.repository.ApiService
import org.koin.dsl.module

object ApiModule {
    fun module() = module {
        single {
            get<ApiGenerator>().api(ApiService::class.java)
        }
    }
}