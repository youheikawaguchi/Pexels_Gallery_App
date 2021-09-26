package com.android.y_kawaguchi.pexels_gallery_app

import android.app.Application
import com.android.y_kawaguchi.pexels_gallery_app.di.ApiModule
import com.android.y_kawaguchi.pexels_gallery_app.di.NetworkModule
import com.android.y_kawaguchi.pexels_gallery_app.di.RepositoryModule
import com.android.y_kawaguchi.pexels_gallery_app.di.ViewModelModule
import org.koin.core.context.startKoin

class App: Application() {

    companion object {
        const val API_KEY = "563492ad6f91700001000001af1494badba24f48873289bc907ae3cb"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    NetworkModule.module(),
                    ApiModule.module(),
                    RepositoryModule.module(),
                    ViewModelModule.module()
                )
            )
        }
    }
}