package com.example.xpayback.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentLoginPageBinding
import com.example.xpayback.network.Api
import com.example.xpayback.network.Resource
import com.example.xpayback.response.AuthBody
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.utils.handleApiError
import com.example.xpayback.utils.visible
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


class LoginPage : BaseFragment<AuthViewModel, FragmentLoginPageBinding, AuthRepository>() {


    private var countryCode : String? = null
    lateinit var ccp: CountryCodePicker
    private var phone: String? = null
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentLoginPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.sendOtpResponse.observe(viewLifecycleOwner) {
            binding.progress.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {

                    val bundle = Bundle()
                    bundle.putString("phone",phone)
                    bundle.putString("key",it.value.key)
                    Toast.makeText(requireContext(), it.value.key, Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_loginPage_to_verificationPhFragment, bundle)
                }
                is Resource.Failure -> handleApiError(it)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            //onDestroy()
            requireActivity().finish()
        }
        binding.btnSubmit.setOnClickListener {


            getPhoneNumber()

        }
    }

    private fun getPhoneNumber() {
        ccp = binding.ccp
        phone = binding.edtPhNumber.text.toString()
        val check = binding.checkBox
        validated(phone!!)
    }

    private fun validated(phone: String) {

        when {

            phone.isEmpty() -> binding.edtPhNumber.error = "Enter Phone Number"
            phone.length != 10 -> binding.edtPhNumber.error = "Enter Correct Phone Number"
            else -> {
                 countryCode = ccp.selectedCountryCodeWithPlus

                lifecycleScope.launchWhenResumed {
                    viewModel.onPassPhoneNumber(phone)

                }





            }

        }
    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java),userPreferences)
}

