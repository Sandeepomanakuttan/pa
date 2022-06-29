package com.example.xpayback.utils

interface AuthInterface {

    fun onSend()

    fun onSuccess()

    fun onFailed(toString: String)

    fun onCompleted()
}