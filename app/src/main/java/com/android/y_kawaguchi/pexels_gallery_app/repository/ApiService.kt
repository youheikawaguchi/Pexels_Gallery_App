package com.android.y_kawaguchi.pexels_gallery_app.repository

import com.android.y_kawaguchi.pexels_gallery_app.App
import com.android.y_kawaguchi.pexels_gallery_app.repository.model.PexelsSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getSearch(
        @Header("Authorization") authorization: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Response<PexelsSearch>

}