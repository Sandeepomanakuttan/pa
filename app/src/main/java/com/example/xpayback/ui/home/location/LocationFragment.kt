package com.example.xpayback.ui.home.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_location.view.*
import kotlinx.android.synthetic.main.fragment_maps.view.*

class LocationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.location.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_mapsFragment)
        }

        view.add_address.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_locationEditFragment)
        }

        view.btnback.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_homeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }
    }


}