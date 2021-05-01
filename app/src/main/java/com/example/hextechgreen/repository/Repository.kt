package com.example.hextechgreen.repository

import com.example.hextechgreen.api.RetrofitInstance
import com.example.hextechgreen.model.Post

class Repository {
    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}