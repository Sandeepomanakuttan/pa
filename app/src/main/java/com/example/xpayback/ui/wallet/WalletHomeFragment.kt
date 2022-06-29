package com.example.xpayback.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.ui.home.kyc.UpdateuccefullyFragment
import kotlinx.android.synthetic.main.fragment_wallet_home.*
import kotlinx.android.synthetic.main.fragment_wallet_home.view.*


class WalletHomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wallet_home, container, false)

        val pef = requireActivity().getSharedPreferences(SetUpXpayWalletFragment().preff,0)
        val checker = pef.getBoolean("kyc",false)

        if (checker){
            view.ActiveWallet.isVisible=false
            view.ActivatedWallet.isVisible=true
        }else{
            view.ActiveWallet.isVisible=true
            view.ActivatedWallet.isVisible=false

        }
        val sp = requireActivity().getSharedPreferences(UpdateuccefullyFragment().preff,0)
        val check = sp.getBoolean("success",false)

        if (check){
            view.getKyc.isVisible=false
            view.verifyKyc.isVisible=true

        }
        view.proceed.setOnClickListener {
            val bundle = bundleOf("set" to true)
            Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_kycFragment,bundle)
        }

        view.active.setOnClickListener {
           Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_setUpXpayWalletFragment)
        }
        view.btn_back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_profileFragment)
        }

        view.topUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_topUpFragment)
        }

        view.applyCard.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_cardFragment)
        }

        view.scanPay.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_walletHomeFragment_to_QrFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback {

        }
        return view
    }

}