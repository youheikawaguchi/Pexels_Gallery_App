package com.android.y_kawaguchi.pexels_gallery_app.view.gallery

import android.app.AlertDialog
import android.util.Log
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
    val isLoading = MutableLiveData(false)
    val currentQuery = MutableLiveData("")
    val isApiStatus = MutableLiveData(false)

    fun getSearchData(query: String, page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when(val apiResult = searchRepository.getSearchPhoto(query, page)){
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
                    isLoading.value = false
                }
            }
        }
    }
}