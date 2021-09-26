package com.android.y_kawaguchi.pexels_gallery_app.repository.model

import java.io.Serializable

data class PexelsPhoto(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val photographerId: Int,
    val avgColor: String,
    val src: SrcEntity,
    val liked: Boolean? = false
) : Serializable

data class SrcEntity(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
) : Serializable