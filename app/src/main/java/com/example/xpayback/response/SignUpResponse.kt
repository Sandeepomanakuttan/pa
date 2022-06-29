package com.example.xpayback.response

data class SignUpResponse(
    val email: String,
    val id: Int,
    val phonenumber: String,
    val role: Int,
    val username: String,
    val created_at: String
)