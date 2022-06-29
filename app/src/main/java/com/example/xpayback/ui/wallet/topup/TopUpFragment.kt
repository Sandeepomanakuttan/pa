package com.example.xpayback.ui.wallet.topup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.R
import kotlinx.android.synthetic.main.cards.*
import kotlinx.android.synthetic.main.fragment_kyc.*
import kotlinx.android.synthetic.main.fragment_top_up.*
import kotlinx.android.synthetic.main.fragment_top_up.view.*


class TopUpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_up, container, false)

            view.btn_back.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_topUpFragment_to_walletHomeFragment)
            }

        view.radio_group.clearCheck()

        view.radio_group.setOnCheckedChangeListener { radioGroup, i ->

            when(i){
                R.id.debit->{
                    view.debit_layout.isVisible=true
                    view.credit_layout.isVisible=false
                    view.banking_layout.isVisible=false
                    proceed.setOnClickListener {
                        Navigation.findNavController(view).navigate(R.id.action_topUpFragment_to_topupReciptFragment)
                    }

                }
                R.id.credit ->{
                    view.credit_layout.isVisible=true
                    view.debit_layout.isVisible=false
                    view.banking_layout.isVisible=false

                }
                R.id.net_banking ->{
                    view.credit_layout.isVisible=false
                    view.debit_layout.isVisible=false
                    view.banking_layout.isVisible=true
                }
                R.id.bhim_upi ->{
                    view.credit_layout.isVisible=false
                    view.debit_layout.isVisible=false
                    view.banking_layout.isVisible=false
                }
                R.id.wallet ->{
                    view.credit_layout.isVisible=false
                    view.debit_layout.isVisible=false
                    view.banking_layout.isVisible=false
                }

            }
        }
        var amount:String?
        view.increase.setOnClickListener {
            amount =view.amount.text.toString()
            if (amount==""){
                amount="0"
            }
            val a=amount?.toInt()?:0
            amount = a.plus(1).toString()
            view.amount.setText(amount)

        }

        view.decrease.setOnClickListener {
            amount =view.amount.text.toString()
            if (amount=="") {
                amount="0"
            }
                val a = amount?.toInt()

            if (a != null) {
                if(a>0)
                    amount = a.minus(1).toString()
            }

            view.amount.setText(amount)

        }
        requireActivity().onBackPressedDispatcher.addCallback {  }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}