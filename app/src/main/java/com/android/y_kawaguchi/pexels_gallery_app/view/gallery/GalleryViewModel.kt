package com.android.y_kawaguchi.pexels_gallery_app.view.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.y_kawaguchi.pexels_gallery_app.repository.ApiResult
import com.android.y_kawaguchi.pexels_gallery_app.repository.SearchRepository
import com.android.y_kawaguchi.pexels_gallery_app.repository.model.PexelsPhoto
import kotlinx.coroutines.launch

class GalleryViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    val photoList = mutableListOf<PexelsPhoto>()
    var currentPage: Int = 0
    var nextPage: Int = 0
    private var navigation: GalleryNavigation? = null
    val isLoading = MutableLiveData(false)
    val currentQuery = MutableLiveData("")
    val isApiStatus = MutableLiveData(false)

    fun setNavigation(navigation: GalleryNavigation) {
        this.navigation = navigation
    }

    fun getSearchData(query: String, page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val apiResult = searchRepository.getSearchPhoto(query, page)) {
                is ApiResult.Success -> {
                    apiResult.value.nextPage?.let {
                        nextPage++
                    }
                    apiResult.value.photos.forEach {
                        photoList.add(it)
                    }
                    isLoading.value = false
                    isApiStatus.value = true
                }
                is ApiResult.Error -> {
                    navigation?.apiError()
                    isLoading.value = false
                }
            }
        }
    }

    fun clickItem(data: PexelsPhoto) {
        navigation?.clickItem(data)
    }
}