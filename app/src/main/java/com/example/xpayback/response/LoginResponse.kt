package com.example.xpayback.response

data class LoginResponse(
    val User :String,
    val Message: String,
    val Status: String,
    val access_token: String,
    val status_code: Int
)