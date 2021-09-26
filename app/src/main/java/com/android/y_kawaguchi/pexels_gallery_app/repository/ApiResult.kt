package com.android.y_kawaguchi.pexels_gallery_app.repository

import android.util.Log
import com.android.y_kawaguchi.pexels_gallery_app.App
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()

    object Error : ApiResult<Nothing>()
}