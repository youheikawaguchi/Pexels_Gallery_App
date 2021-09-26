package com.android.y_kawaguchi.pexels_gallery_app.repository

data class ApiResponse<out T>(
    val response: T?,
    val code: String?,
    val status: Int?
)
