package com.example.xpayback.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentSignUp2Binding
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment

class SignUpFragment2 : BaseFragment<AuthViewModel,FragmentSignUp2Binding,AuthRepository>() {
    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignUp2Binding.inflate(inflater,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_signUpFragment2_to_signupFragment3)
        }
    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java))


}