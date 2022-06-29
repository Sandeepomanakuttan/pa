package com.example.xpayback.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.network.UserPreferences
import com.example.xpayback.ui.auth.VerificationPhFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val status = arguments?.getBoolean("status")


        view.Setting.setOnClickListener{

            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_settingFragment)
        }

        view.findViewById<ConstraintLayout>(R.id.logout).setOnClickListener {

            val preffer=requireActivity().getSharedPreferences(VerificationPhFragment().authTokenPreff,0)
            val edit=preffer.edit()
            edit.putString("token",null)
            edit.apply()

            val userPreferences = UserPreferences(requireContext())

            lifecycleScope.launchWhenResumed {
                userPreferences.clear()
            }

            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_loginPage)
        }

        view.profile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        view.btb_back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_homeFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback {

            if (status==true){
                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_homeFragment)
            }
        }

        view.wallet.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_walletHomeFragment)
        }
        return view


    }


}