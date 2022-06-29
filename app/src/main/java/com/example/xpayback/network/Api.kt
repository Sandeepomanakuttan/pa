package com.example.xpayback.network

import com.example.xpayback.response.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

  @POST("login/phonenumber")
  suspend fun loginPhoneNumber(@Query("phone_number") phone_number:String):AuthResponse

  @POST("login/otp")
  suspend fun otpVerification(@Query("otp") otp: String,@Query ("phonenumber") phone_number: String,@Query("key") key:String):LoginResponse

  @POST("signup/")
  suspend fun signUp(@Body data:RequestSignUpBody):SignUpResponse


  @POST("user/me/")
  suspend fun getUser():UsersResponseItem


}


