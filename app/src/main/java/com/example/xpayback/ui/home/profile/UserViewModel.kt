package com.example.xpayback.ui.home.profile

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
class UserViewModel(private val repository: UserRepository) :ViewModel() {


    private val _userResponse:MutableLiveData<Resource<UsersResponseItem>> = MutableLiveData()
    val userResponse:LiveData<Resource<UsersResponseItem>>
        get() = _userResponse


    suspend fun getUser(){
        _userResponse.value=Resource.Loading
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