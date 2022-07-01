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
import com.example.xpayback.network.UserApi
import com.example.xpayback.network.UserPreferences
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.auth.VerificationPhFragment
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.utils.handleApiError
import com.example.xpayback.utils.visible
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class EditProfileFragment : BaseFragment<UserViewModel,FragmentEditProfileBinding,UserRepository>() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel() = UserViewModel::class.java

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

        viewModel.userResponse.observe(viewLifecycleOwner){
            progress_layout.visible(it is Resource.Loading)
            when(it){
                is Resource.Success ->{
                    txtName.text=it.value.username
                    txtPhone.text=it.value.phonenumber.toString()
                    txtEmail.text=it.value.email
                    Toast.makeText(requireContext(), it.value.phonenumber.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure->handleApiError(it)
            }
        }

        binding.editControl.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_kycFragment)

        }

        binding.btbBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    override fun getFragmentRepository() : UserRepository{
        val token = runBlocking { userPreferences.accessToken.first() }
        return UserRepository(retrofitInstances.buildApi(UserApi::class.java,token))}



}