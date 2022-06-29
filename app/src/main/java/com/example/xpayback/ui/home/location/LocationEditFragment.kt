package com.example.xpayback.ui.home.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_location_edit.*
import kotlinx.android.synthetic.main.fragment_location_edit.view.*

class LocationEditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_edit, container, false)

        val add = arguments?.getString("value")
        view.btnback.setOnClickListener {

        }

        view.local_address.setText(add)

        view.btnSubmit.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_locationEditFragment_to_locationFragment)
        }

        view.btnback.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_locationEditFragment_to_locationFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }

        return view
    }

}