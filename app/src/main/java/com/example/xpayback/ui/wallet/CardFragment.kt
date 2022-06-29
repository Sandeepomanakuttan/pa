package com.example.xpayback.ui.wallet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_card.rdogrp
import kotlinx.android.synthetic.main.fragment_card.view.*
import kotlinx.android.synthetic.main.fragment_kyc.*


class CardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_card, container, false)

        view.btnback.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_cardFragment_to_walletHomeFragment)
        }

        view.rdogrp.setOnCheckedChangeListener { radioGroup, i ->

            when(i){
                R.id.digital -> {
                    digital.setTextColor(Color.WHITE)
                    physical_card_layout.isVisible=false
                    layout.isVisible=true
                    physical.setTextColor(Color.parseColor("#005D98"))}
                R.id.physical -> {
                    physical.setTextColor(Color.WHITE)
                    physical_card_layout.isVisible=true
                    layout.isVisible=false
                    digital.setTextColor(Color.parseColor("#005D98"))
                }
                else ->{}
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }
        return view
    }


}