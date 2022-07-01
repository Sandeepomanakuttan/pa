package com.example.xpayback.ui.home.profile

import com.example.xpayback.network.Api
import com.example.xpayback.network.UserApi
import com.example.xpayback.response.AuthResponse
import com.example.xpayback.response.LoginResponse
import com.example.xpayback.response.RequestSignUpBody
import com.example.xpayback.response.SignUpResponse
import com.example.xpayback.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val api : UserApi):BaseRepository() {



    suspend fun getUser()=safeApiCall{api.getUser()}

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