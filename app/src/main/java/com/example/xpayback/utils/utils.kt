package com.example.xpayback.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.xpayback.network.Resource
import com.example.xpayback.ui.auth.LoginPage
import com.example.xpayback.ui.auth.VerificationPhFragment
import com.google.android.material.snackbar.Snackbar

fun View.toast(msg:String){

    Toast.makeText(context, "msg", Toast.LENGTH_SHORT).show()
}

fun View.visible(isVisible : Boolean){
    visibility = if(isVisible) View.VISIBLE else View.GONE
}

fun View.snackbar(message : String ,action :(() -> Unit) ?=null){

    val snackbar = Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    action?.let {
         snackbar.setAction("Retry"){
             it()
         }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure : Resource.Failure,
    retry : (() -> Unit)? = null
){
    when{
        failure.isNetworkError == true -> requireView().snackbar("Please check your internet Connection",retry)
        failure.errorCode == 401 ->{
            if (this is LoginPage){

            }else {
                requireView().snackbar("You've entered incorrect email or password")
            }
        }
        failure.errorCode ==403 ->{
            requireView().snackbar("403 error")
        }
        failure.errorCode ==404 ->{
            if (this is VerificationPhFragment) {
                requireView().snackbar("Otp Incorrect")
            }
        }
        else ->{
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}
