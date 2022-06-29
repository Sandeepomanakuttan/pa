package com.example.xpayback.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.auth.FirebaseSource
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
              modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(
                repository as AuthRepository) as T
            else -> {throw IllegalArgumentException("unknown View model class")}
        }
    }


}