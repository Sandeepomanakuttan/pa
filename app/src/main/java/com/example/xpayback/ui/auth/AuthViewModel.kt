package com.example.xpayback.ui.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xpayback.MainActivity
import com.example.xpayback.network.Resource
import com.example.xpayback.response.*
import com.example.xpayback.utils.AuthInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class AuthViewModel(private val repository: AuthRepository) :ViewModel() {


    private val _userResponse:MutableLiveData<Resource<UsersResponseItem>> = MutableLiveData()
    val userResponse:LiveData<Resource<UsersResponseItem>>
        get() = _userResponse




    private val _sendOtpResponse : MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val sendOtpResponse:LiveData<Resource<AuthResponse>>
        get() = _sendOtpResponse

    private val _signUpResponse : MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()
    val signUpResponse:LiveData<Resource<SignUpResponse>>
        get() = _signUpResponse


    private val OtpResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val OtpRespo:LiveData<Resource<LoginResponse>>
        get() = OtpResponse

    suspend fun onPassPhoneNumber(number : String) {
        _sendOtpResponse.value=Resource.Loading
        _sendOtpResponse.value = repository.onPassingPhoneNumber(number)}



    suspend fun onSubmitOtp(code: String, number: String, key: String) {
        OtpResponse.value=Resource.Loading
        OtpResponse.value = repository.onSubmitOtp(code, number, key)
    }

    suspend fun onRegitration(data: RequestSignUpBody) {
        _signUpResponse.value=Resource.Loading
        _signUpResponse.value=repository.onRegitration(data)
    }

    suspend fun getUser(){
        _userResponse.value=repository.getUser()
    }


//    fun onSubmitOtp(code: String)= viewModelScope.launch {
//        _OtpResponse.value =repository.onSubmitOtp(code) }

//    fun passActivity(activity: MainActivity, auth: AuthInterface?) = repository.passActivity(activity,auth)


//    fun sendVerificationCode(Phone: String) {
//         repository.sendVerificationCode(Phone)
//    }
//
//    fun getOtp(){
//        _otp?.value=repository.getOtp()
//        Log.e("viewModel",_otp?.value.toString())
//    }
//
//    fun signInWithCredential(phoneAuthcredential: PhoneAuthCredential) =repository.signInWithCredential(phoneAuthcredential)


//    fun onCheckUserExist(number: String)=repository.onCheckUserExist(number)



}