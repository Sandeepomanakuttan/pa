package com.example.xpayback.ui.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentSignUp4Binding
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment

class SignUpFragment4 : BaseFragment<AuthViewModel,FragmentSignUp4Binding,AuthRepository>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignUp4Binding.inflate(inflater,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.root
        binding.txtSkip.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signUpFragment4_to_homeFragment)
        }
    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java))


}