package com.android.y_kawaguchi.pexels_gallery_app.repository.model

import java.io.Serializable

data class PexelsSearch(
    val photos: List<PexelsPhoto>,
    val page: Int,
    val perPage: Int,
    val totalResults: Int,
    val prevPage: String?,
    val nextPage: String?,
): Serializable
