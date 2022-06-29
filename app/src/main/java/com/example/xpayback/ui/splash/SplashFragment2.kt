package com.example.xpayback.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.ui.auth.VerificationPhFragment


class SplashFragment2 : Fragment() {

    val splashPref="SplashPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash2, container, false)

        val finish = view.findViewById<ImageView>(R.id.btnNext)

        finish.setOnClickListener {

            val sp = requireActivity().getSharedPreferences(SplashFragment2().splashPref,0);
            val edit = sp.edit()
            edit.putBoolean("splash",true)
            edit.apply()

            Navigation.findNavController(view)
                .navigate(R.id.action_splashFragment2_to_loginPage)

//            if (checker) {
//                Navigation.findNavController(view).navigate(R.id.action_splashFragment2_to_homeFragment)
//            }else{

           // }
        }

        return view
    }

}