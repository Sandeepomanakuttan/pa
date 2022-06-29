package com.example.xpayback.ui.home.profile

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentEditProfileBinding
import com.example.xpayback.network.Api
import com.example.xpayback.network.Resource
import com.example.xpayback.network.UserPreferences
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.auth.VerificationPhFragment
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.utils.handleApiError
import kotlinx.android.synthetic.main.fragment_edit_profile.*


class EditProfileFragment : BaseFragment<AuthViewModel,FragmentEditProfileBinding,AuthRepository>() {

    private var loginChecker:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentEditProfileBinding.inflate(inflater,container,false)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {

            viewModel.getUser()
        }

        binding.editControl.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_kycFragment)

        }

        binding.btbBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }

        val userPreferences = UserPreferences(requireContext())

        userPreferences.accessToken.asLiveData().observe(viewLifecycleOwner){

            loginChecker = it

        }

        viewModel.userResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), it.value.phonenumber.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure->handleApiError(it)
            }
        }





    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userPreferences = UserPreferences(requireContext())

        userPreferences.accessToken.asLiveData().observe(viewLifecycleOwner){ it ->

            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()


        }
    }


    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java,loginChecker))



}