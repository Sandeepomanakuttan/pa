package com.example.xpayback.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentVerificationPhBinding
import com.example.xpayback.network.Api
import com.example.xpayback.network.Resource
import com.example.xpayback.network.UserPreferences
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.utils.handleApiError
import com.example.xpayback.utils.visible
import kotlinx.android.synthetic.main.fragment_login_page.*

class VerificationPhFragment :
    BaseFragment<AuthViewModel, FragmentVerificationPhBinding, AuthRepository>() {



    private lateinit var phone: TextView
    private lateinit var code1: EditText
    private lateinit var code2: EditText
    private lateinit var code3: EditText
    private lateinit var code4: EditText
    private lateinit var code5: EditText
    private lateinit var code6: EditText
    lateinit var number:String
    private var key: String? = null
    var authTokenPreff = "MyAuth"
    var preffName = "myPreff"
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentVerificationPhBinding.inflate(inflater, container, false)

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        phone = binding.txtPh
        code1 = binding.code1
        code2 = binding.code2
        code3 = binding.code3
        code4 = binding.code4
        code5 = binding.code5
        code6 = binding.code6
        key = arguments?.getString("key")
        number = arguments?.getString("phone").toString()

        binding.txtPh.text = number






        binding.progress.isVisible = false
        binding.btnSubmit.isVisible = true




        viewModel.sendOtpResponse.observe(viewLifecycleOwner) {
            binding.progress.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {

                    key =it.value.key
                }
                is Resource.Failure -> handleApiError(it)
            }
        }





        viewModel.OtpRespo.observe(viewLifecycleOwner) {

            binding.progress.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
//                    val preffer =
//                        requireActivity().getSharedPreferences(VerificationPhFragment().preffName,
//                            0)
//                    val edit = preffer.edit()
//                    edit.putBoolean("loggedIn", true)
//                    edit.apply()
//                    val auth =
//                        requireActivity().getSharedPreferences(VerificationPhFragment().authTokenPreff,
//                            0)
//                    val editer = auth.edit()
//                    editer.putString("token", it.value.access_token)
//                    editer.apply()
//                    binding.progress.isVisible = false
//                    binding.btnSubmit.isVisible = true
                    val bundle = Bundle()



                    lifecycleScope.launchWhenResumed {
                        viewModel.saveAccessTokens(it.value.access_token)
                        Toast.makeText(requireContext(), it.value.access_token, Toast.LENGTH_SHORT).show()
                    }

                        number.let { it1 -> bundle.putString("number", it1)}
                    if (it.value.User == "New User") {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_verificationPhFragment_to_signupFragment1, bundle)
                        return@observe

                    }else{

                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_verificationPhFragment_to_homeFragment, bundle)
                        return@observe
                    }

                }

                is Resource.Failure-> {
                    handleApiError(it)
                    binding.btnSubmit.isVisible=true
                }
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_verificationPhFragment_to_login)
        }






        setupOtpInput()


        binding.btnSubmit.setOnClickListener {

            binding.btnSubmit.isVisible = false
            otpVerification()

        }

        binding.resend.setOnClickListener {

            lifecycleScope.launchWhenResumed {
                viewModel.onPassPhoneNumber(number)

            }

        }


    }

    private fun otpVerification() {


        if (code1.text.toString().trim { it <= ' ' }.isEmpty()
            || code2.text.toString().trim { it <= ' ' }.isEmpty()
            || code3.text.toString().trim { it <= ' ' }.isEmpty()
            || code4.text.toString().trim { it <= ' ' }.isEmpty()
            || code5.text.toString().trim { it <= ' ' }.isEmpty()
            || code6.text.toString().trim { it <= ' ' }.isEmpty()
        ) {
            Toast.makeText(
                requireContext(),
                "Please Enter Valid code",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val code: String = (code1.text.toString()
                + code2.text.toString() +
                code3.text.toString() +
                code4.text.toString() +
                code5.text.toString() +
                code6.text.toString())

        lifecycleScope.launchWhenResumed {
            number.let { key?.let { it1 -> viewModel.onSubmitOtp(code, it, it1) } }

        }
        Log.e("it", "passed")


    }

    private fun setupOtpInput() {

        code1.addTextChangedListener(GenericTextWatcher(code1, code2))
        code2.addTextChangedListener(GenericTextWatcher(code2, code3))
        code3.addTextChangedListener(GenericTextWatcher(code3, code4))
        code4.addTextChangedListener(GenericTextWatcher(code4, code5))
        code5.addTextChangedListener(GenericTextWatcher(code5, code6))
        code6.addTextChangedListener(GenericTextWatcher(code6, null))


        code1.setOnKeyListener(GenericKeyEvent(code1, null))
        code2.setOnKeyListener(GenericKeyEvent(code2, code1))
        code3.setOnKeyListener(GenericKeyEvent(code3, code2))
        code4.setOnKeyListener(GenericKeyEvent(code4, code3))
        code5.setOnKeyListener(GenericKeyEvent(code5, code4))
        code6.setOnKeyListener(GenericKeyEvent(code6, code5))

    }


    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java),userPreferences)


}

class GenericKeyEvent internal constructor(
    private val currentView: EditText,
    private val previousView: EditText?,
) : View.OnKeyListener {
    override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.code1 && currentView.text.isEmpty()) {
            //If current is empty then previous EditText's number will also be deleted
            previousView!!.text = null
            previousView.requestFocus()
            return true
        }
        return false
    }


}

class GenericTextWatcher internal constructor(
    private val currentView: View,
    private val nextView: View?,
) : TextWatcher {
    override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
        val text = editable.toString()
        when (currentView.id) {
            R.id.code1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.code2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.code3 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.code4 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.code5 -> if (text.length == 1) nextView!!.requestFocus()
            //You can use EditText4 same as above to hide the keyboard
        }
    }

    override fun beforeTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int,
    ) { // TODO Auto-generated method stub
    }

    override fun onTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int,
    ) { // TODO Auto-generated method stub
    }

}


