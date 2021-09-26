package com.android.y_kawaguchi.pexels_gallery_app.di

import com.android.y_kawaguchi.pexels_gallery_app.view.gallery.GalleryViewModel
import com.android.y_kawaguchi.pexels_gallery_app.view.gallery.details.PhotoDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    fun module() = module {
        viewModel { GalleryViewModel(get()) }
        viewModel { PhotoDetailsViewModel() }
    }
}