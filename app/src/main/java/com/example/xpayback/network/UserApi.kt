package com.example.xpayback.network

import com.example.xpayback.response.UsersResponseItem
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user/me/")
    suspend fun getUser(): UsersResponseItem

}