package com.example.hextechgreen.api

import com.example.hextechgreen.model.Post
import retrofit2.http.GET

interface PinApi {
    @GET("mini/test")
    suspend fun getPost(): Post
}