package com.example.xpayback.ui.wallet.topup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_topup_recipt.view.*


class TopupReciptFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_topup_recipt, container, false)

        view.btnback.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_topupReciptFragment_to_walletHomeFragment)
        }

        view.btnDone.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_topupReciptFragment_to_walletHomeFragment)
        }
        return view
    }


}