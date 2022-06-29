package com.example.xpayback.response

data class UsersResponseItem(
    val email: String,
    val id: Int,
    val password: String,
    val phonenumber: Long,
    val role: Int,
    val username: String
)