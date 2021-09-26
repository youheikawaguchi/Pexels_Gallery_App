package com.android.y_kawaguchi.pexels_gallery_app.network

import retrofit2.Retrofit

class ApiGenerator(val builder: Retrofit.Builder) {

    fun <T> api(clazz: Class<T>): T = builder.baseUrl("https://api.pexels.com/v1/").build().create(clazz)

}