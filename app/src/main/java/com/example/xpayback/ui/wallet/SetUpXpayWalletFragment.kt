package com.example.xpayback.ui.wallet

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xpayback.R
import kotlinx.android.synthetic.main.fragment_set_up_xpay_wallet.view.*

class SetUpXpayWalletFragment : Fragment() {

    var preff ="myPreff"

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_set_up_xpay_wallet, container, false)

        val arrayList=ArrayList<Doc>()

        arrayList.add(Doc(R.drawable.ic_passport_,"Passport"))
        arrayList.add(Doc(R.drawable.ic_pancard,"Pancard"))
        arrayList.add(Doc(R.drawable.ic_aadhaar,"Aadhar card"))

        val adaptor = KycRecyclerView()
        adaptor.item=arrayList
        view.recycler.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=adaptor
            setHasFixedSize(true)
        }

        view.btnSubmit.setOnClickListener {
            val validate = requireActivity().getSharedPreferences(SetUpXpayWalletFragment().preff,0)
            val edit = validate.edit()
            edit.putBoolean("kyc",true)
            edit.apply()
            Navigation.findNavController(view).navigate(R.id.action_setUpXpayWalletFragment_to_walletHomeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            Navigation.findNavController(view).navigate(R.id.action_setUpXpayWalletFragment_to_walletHomeFragment)
        }

        return view
    }


}