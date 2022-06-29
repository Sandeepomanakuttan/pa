package com.example.xpayback.ui.auth

import android.util.Log
import android.widget.Toast
import com.example.xpayback.MainActivity
import com.example.xpayback.utils.AuthInterface
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class FirebaseSource {

    var activity: MainActivity?=null
    var auth: AuthInterface?=null
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    var otp:String?=null
    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser
    fun sendVerificationCode(phone: String) {

            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity!!) // Activity (for callback binding)
                .setCallbacks(mCallbacks) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

    }

    fun signInWithCredential(phoneAuthcredential: PhoneAuthCredential) {

        FirebaseAuth.getInstance().signInWithCredential(phoneAuthcredential).addOnCompleteListener {
            if(it.isSuccessful) auth?.onCompleted()
        }.addOnFailureListener {
            auth?.onFailed(it.toString())
        }

    }


    private val  mCallbacks :PhoneAuthProvider.OnVerificationStateChangedCallbacks= object :PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            auth?.onSuccess()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            auth?.onFailed(p0.toString())
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
           otp = verificationId
            auth?.onSend()
        }


    }

}