package com.example.xpayback.ui.home.kyc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_updateuccefully.view.*

class UpdateuccefullyFragment : Fragment() {

    var preff = "MyPref"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback {

        }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_updateuccefully, container, false)
        val status = arguments?.getBoolean("wallet")
        view.btnSubmit.setOnClickListener {
            if (status == true) {
                val sp = requireActivity().getSharedPreferences(preff, 0)
                val edit = sp.edit()
                edit.putBoolean("success", true)
                edit.apply()
                Navigation.findNavController(view)
                    .navigate(R.id.action_updateuccefullyFragment_to_walletHomeFragment)
                return@setOnClickListener
            }
            Navigation.findNavController(view)
                .navigate(R.id.action_updateuccefullyFragment_to_profileFragment)

        }

        return view
    }


}