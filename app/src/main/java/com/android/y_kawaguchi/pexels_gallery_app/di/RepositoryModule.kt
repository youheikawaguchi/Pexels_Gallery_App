package com.android.y_kawaguchi.pexels_gallery_app.di

import com.android.y_kawaguchi.pexels_gallery_app.repository.SearchRepository
import org.koin.dsl.module

object RepositoryModule {
    fun module() = module {
        single {
            SearchRepository(get())
        }
    }
}