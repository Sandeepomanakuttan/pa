package com.example.xpayback.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.xpayback.MenuBottomAdaptor
import com.example.xpayback.R


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        view.findViewById<ImageView>(R.id.btnNext).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_splashFragmentTwo)
           // Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_homeFragment)
           // Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_signUpFragment4)

        }

        return view
    }




}