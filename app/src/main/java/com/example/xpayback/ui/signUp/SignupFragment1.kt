package com.example.xpayback.ui.signUp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentSignup1Binding
import com.example.xpayback.network.Api
import com.example.xpayback.network.Resource
import com.example.xpayback.response.RequestSignUpBody
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.utils.handleApiError
import com.example.xpayback.utils.visible
import java.text.SimpleDateFormat
import java.util.*


class SignupFragment1 : BaseFragment<AuthViewModel,FragmentSignup1Binding,AuthRepository>() {

   // var number:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSignup1Binding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtDob.showSoftInputOnFocus = false;
        binding.edtDob.isCursorVisible = false;

        val number = arguments?.getString("number")

        binding.edtDob.setOnClickListener {
            getDateBirth()
        }
        binding.btnSubmit.setOnClickListener {

            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val dob = binding.edtDob.text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            when{
                name.isEmpty()->{
                    binding.edtName.setBackgroundResource(R.drawable.error_style)
                }
                email.isEmpty()->{
                    binding.edtEmail.setBackgroundResource(R.drawable.error_style)
                    binding.txtEmail.text=getString(R.string.please_enter_email)
                }
                !email.matches(Regex(emailPattern))->{
                    binding.edtEmail.setBackgroundResource(R.drawable.error_style)
                    binding.txtEmail.text=getString(R.string.email)
                }
                dob.isEmpty()->{
                    binding.edtDob.setBackgroundResource(R.drawable.error_style)
                    binding.txtDob.text=getString(R.string.date_valid)
                }

                else->{

                    Toast.makeText(requireContext(), number.toString(), Toast.LENGTH_SHORT).show()


                    val data = number?.let { it1 -> RequestSignUpBody(name,email, it1,0,name,getCurrentDate()) }

                    lifecycleScope.launchWhenResumed {
                        data.let {
                            it.let { it2 ->
                                if (it2 != null) {
                                    viewModel.onRegitration(it2)
                                }
                            }
                        }
                    }

                    viewModel.signUpResponse.observe(viewLifecycleOwner) {
                       binding.progress.visible(it is Resource.Loading )
                        when(it){
                            is Resource.Success->{
                                val id = bundleOf("id" to id)
                                    Navigation.findNavController(binding.root).navigate(R.id.action_signupFragment1_to_signUpFragment2,id)
                                    return@observe
                            }
                            is Resource.Failure->handleApiError(it)
                        }
//                        it?.id.let {
//
//                        }

                        Toast.makeText(requireContext(), "faild", LENGTH_SHORT).show()
                    }

                }
            }
        }

        binding.root

    }

    private fun getDateBirth() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar[Calendar.YEAR]
        val month = myCalendar[Calendar.MONTH]
        val day = myCalendar[Calendar.DAY_OF_MONTH]

        val dpd= DatePickerDialog(requireContext(),{ _, selectedYear, selectedMonth, selectedDay->

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            binding.edtDob.setText(selectedDate)
        },year,
            month,
            day
        )

        dpd.show()
    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java),userPreferences)


    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return sdf.format(Date())
    }
}



