package com.example.xpayback.ui.auth

import com.example.xpayback.network.Api
import com.example.xpayback.response.AuthResponse
import com.example.xpayback.response.LoginResponse
import com.example.xpayback.response.RequestSignUpBody
import com.example.xpayback.response.SignUpResponse
import com.example.xpayback.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(private val api : Api):BaseRepository() {




    suspend fun onPassingPhoneNumber(number: String) = safeApiCall{
        api.loginPhoneNumber(number) }

    suspend fun onSubmitOtp(code: String, number: String, key: String) = safeApiCall {
        api.otpVerification(code,number,key)
    }

    suspend fun onRegitration(data: RequestSignUpBody) =safeApiCall { api.signUp(data) }

    suspend fun getUser()=safeApiCall{api.getUser()}

    // suspend fun onSubmitOtp(code: String) = safeApiCall { api.otpVerification(code) }

    }


//    val firebase =FirebaseSource()
//    fun passActivity(activity: MainActivity, auth: AuthInterface?){ firebase.activity=activity
//    firebase.auth=auth}
//
//    fun sendVerificationCode(phone: String) { firebase.sendVerificationCode(phone)
//    }
//    fun getOtp(): String {
//        val v =firebase.otp
//        return v.toString()
//
//    }
//
//    fun signInWithCredential(phoneAuthcredential: PhoneAuthCredential) = firebase.signInWithCredential(phoneAuthcredential)
//    fun onCheckUserExist(number: String) =
//}