package com.example.xpayback.ui.home.kyc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentKycBinding
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_kyc.*


class KycFragment : BaseFragment<AuthViewModel,FragmentKycBinding,AuthRepository>() {

    var user:String?=null
    var gender:String?=null



    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentKycBinding.inflate(inflater,container,false)

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var title = "Disclaimer: "
        var dis =
            "Please Upload self attested document and also ensure name/Address Proof /ID number should be as per documents uploaded."
        var sourceString = "<b>$title</b> $dis"
        txt_declarations.text = Html.fromHtml(sourceString)

        title = "Note: "
        dis = "Only PNG and JPEG file format are allowed and file size must not exceed 2.0 MB."

        sourceString = "<b>$title</b> $dis"
        txt_Notes.text = Html.fromHtml(sourceString)


        var type = resources.getStringArray(R.array.User_Type)
        var adapter: ArrayAdapter<String>?

        if (spinner != null) {
             adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1, type
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    user = type[position]

                    (parent?.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            type = resources.getStringArray(R.array.Gender)


            if (spinnerGender != null) {
                adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1, type
                )
                spinnerGender.adapter = adapter

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        gender = type[position]

                        (parent?.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            }


            rdogrp.setOnCheckedChangeListener { radioGroup, i ->

                when(i){
                    R.id.btnID -> {
                        c1.isVisible=false
                        pan_card.isVisible=true
                        btnID.setTextColor(Color.WHITE)
                    btnAddress.setTextColor(Color.parseColor("#005D98"))}
                    R.id.btnAddress -> {
                        btnAddress.setTextColor(Color.WHITE)
                        btnID.setTextColor(Color.parseColor("#005D98"))
                        pan_card.isVisible=false
                        c1.isVisible=true
                    }
                    else ->{}
                }
                }
            }
        val page = Bundle()
        val status = arguments?.getBoolean("set")


        Aadhaar.setOnClickListener {

                if (status != null) {
                    page.putBoolean("page",status)
                }
                page.putString("id_type","Aadhaar")
              Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }

        pan_card.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }
        passport.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }
        voter_id.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }
        driving.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }
        nrega_id.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_kfcRegistractionFragment,page)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }


        binding.btbBack.setOnClickListener {


            Navigation.findNavController(view).navigate(R.id.action_kycFragment_to_editProfileFragment,page)
        }


        binding.root
        }
    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java))


}
