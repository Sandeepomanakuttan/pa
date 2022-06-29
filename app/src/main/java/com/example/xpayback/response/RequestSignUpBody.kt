package com.example.xpayback.response

data class RequestSignUpBody(
    val username: String,
    val email: String,
    val phonenumber: String,
    val role: Int,
    val password: String,
    val created_at: String,
)

//{
//    "username": "string",
//    "email": "string",
//    "phonenumber": 0,
//    "role": 0,
//    "password": "string"
//}