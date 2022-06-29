package com.example.xpayback

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation

class NotificationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        val status:Boolean? = arguments?.getBoolean("status")


        requireActivity().onBackPressedDispatcher.addCallback {

            if (status == true) {
                Navigation.findNavController(view).navigate(R.id.action_notificationFragment_to_homeFragment)
            }
        }

        return view
    }


}