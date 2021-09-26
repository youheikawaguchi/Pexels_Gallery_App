package com.android.y_kawaguchi.pexels_gallery_app.repository

import com.android.y_kawaguchi.pexels_gallery_app.App
import com.android.y_kawaguchi.pexels_gallery_app.repository.model.PexelsSearch

class SearchRepository(private val api: ApiService) {

    suspend fun getSearchPhoto(query: String, page: Int): ApiResult<PexelsSearch>? {
        var apiResult: ApiResult<PexelsSearch>? = null
        runCatching {
            api.getSearch(App.API_KEY, query, page)
        }.onSuccess {
            apiResult = if (it.isSuccessful) {
                val body = it.body() ?: return ApiResult.Error
                ApiResult.Success(body)
            } else {
                ApiResult.Error
            }
        }.onFailure {
            apiResult = ApiResult.Error
        }
        return apiResult
    }
}