package com.example.xpayback.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentHomeFragmentmainBinding
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.ui.home.model.HomeRecyclerViewItem
import com.example.xpayback.ui.home.model.HomeRecyclerviewAdaptor
import com.example.xpayback.ui.home.model.StoreRecycleView
import kotlinx.android.synthetic.main.fragment_home_fragmentmain.*


class HomeFragmentMain : BaseFragment<AuthViewModel,FragmentHomeFragmentmainBinding,AuthRepository>() {

    private val homeRView = HomeRecyclerviewAdaptor()
    private val storeRView = HomeRecyclerviewAdaptor()
    private val sponsorRView = HomeRecyclerviewAdaptor()
    private val adsRView = HomeRecyclerviewAdaptor()
    private val brandsRView = HomeRecyclerviewAdaptor()
    private val billPayRView = HomeRecyclerviewAdaptor()
    private val donateRView = HomeRecyclerviewAdaptor()



    private lateinit var recyclerView: RecyclerView
    lateinit var storeRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )=FragmentHomeFragmentmainBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView =  binding.walletRecycler
        storeRecyclerView = binding.storeRecycler
        //recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)


        binding.scan.setOnClickListener {

            val bundle = bundleOf("status" to true)

            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_qrScannerFragment,bundle)
        }

        var doubleBackToExitPressedOnce = false

        requireActivity().onBackPressedDispatcher.addCallback {

            if (doubleBackToExitPressedOnce){
                requireActivity().finish()
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        }


        val arr = ArrayList<HomeRecyclerViewItem>()
        val ar = ArrayList<HomeRecyclerViewItem>()
        val a = ArrayList<HomeRecyclerViewItem>()
        val arraylistAds = ArrayList<HomeRecyclerViewItem>()
        val arraylistBrands = ArrayList<HomeRecyclerViewItem>()
        val arraylistBillPay = ArrayList<HomeRecyclerViewItem>()
        val arraylistDonate = ArrayList<HomeRecyclerViewItem>()


        arr.add(HomeRecyclerViewItem.Wallet(R.drawable.ic_wallet,getString(R.string.xpay_wallet)))
        arr.add(HomeRecyclerViewItem.Wallet(R.drawable.ic_gyft,getString(R.string.gyft_wallet)))
        arr.add(HomeRecyclerViewItem.Wallet(R.drawable.ic_my_card,getString(R.string.prepaid_card)))
        arr.add(HomeRecyclerViewItem.Wallet(R.drawable.ic_referal,getString(R.string.refer_earn)))

        homeRView.items= arr

        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_dining,getString(R.string.dinning)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_automobile,getString(R.string.automobiles)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_fashion,getString(R.string.fashion)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_home,getString(R.string.home)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_health,getString(R.string.health)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_sports,getString(R.string.sports)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_entrainments,getString(R.string.entertainment)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_appliances,getString(R.string.appliances)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_groeries,getString(R.string.groceries)))
        ar.add(HomeRecyclerViewItem.Store(R.drawable.ic_more1,getString(R.string.more)))



        arraylistAds.add(HomeRecyclerViewItem.Ads(R.drawable.ads))
        arraylistAds.add(HomeRecyclerViewItem.Ads(R.drawable.ads))

        arraylistBrands.add(HomeRecyclerViewItem.Brands(R.drawable.ic_myntra,getString(R.string.myntra)))
        arraylistBrands.add(HomeRecyclerViewItem.Brands(R.drawable.ic_flipkart,getString(R.string.flipkart)))
        arraylistBrands.add(HomeRecyclerViewItem.Brands(R.drawable.ic_swiggy_1,getString(R.string.swiggy)))
        arraylistBrands.add(HomeRecyclerViewItem.Brands(R.drawable.ic_more,getString(R.string.see_all)))






        recyclerView.apply {

            layoutManager = GridLayoutManager(requireContext(), 4)

            adapter = homeRView

            setHasFixedSize(true)

        }





        ccp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_locationFragment)
        }


        storeRecyclerView.apply {

            storeRView.items=ar
            layoutManager = GridLayoutManager(requireContext(),5)

            adapter = storeRView

            setHasFixedSize(true)
        }

        a.add(HomeRecyclerViewItem.Sponsor(R.drawable.ic_zomato,getString(R.string.zomato)))
        a.add(HomeRecyclerViewItem.Sponsor(R.drawable.ic_shopsy,getString(R.string.shopsy)))
        a.add(HomeRecyclerViewItem.Sponsor(R.drawable.ic_jupiter,getString(R.string.jupiter)))
        a.add(HomeRecyclerViewItem.Sponsor(R.drawable.ic_van,getString(R.string.van)))



        binding.sponcerRecyclerView.apply {

            sponsorRView.items=a
            layoutManager = GridLayoutManager(requireContext(),4)

            adapter = sponsorRView

            setHasFixedSize(true)
        }


        binding.adsRecyclerView.apply {

            adsRView.items=arraylistAds
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)

            adapter = adsRView

            setHasFixedSize(true)
        }

        binding.brandRecyclerView.apply {

            brandsRView.items=arraylistBrands

            layoutManager = GridLayoutManager(requireContext(),4)

            adapter = brandsRView

        }


        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_mobile,getString(R.string.mobile)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_dth,getString(R.string.dth)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_electricity,getString(R.string.electricity)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_pay,getString(R.string.card)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_rent,getString(R.string.rent)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_loan,getString(R.string.loan)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_cilinder,getString(R.string.book)))
        arraylistBillPay.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_more22,getString(R.string.see_all)))



        binding.billRecyclerView.apply {

            billPayRView.items=arraylistBillPay

            layoutManager = GridLayoutManager(requireContext(),4)

            adapter = billPayRView

        }

        arraylistDonate.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_health_day,getString(R.string.World_health)))
        arraylistDonate.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_nanhi,getString(R.string.Nankhli)))
        arraylistDonate.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_meals,getString(R.string.donate_meals)))
        arraylistDonate.add(HomeRecyclerViewItem.BillPay(R.drawable.ic_more22,getString(R.string.see_all)))

        binding.donateRecyclerView.apply {

            donateRView.items=arraylistDonate

            layoutManager = GridLayoutManager(requireContext(),4)

            adapter = donateRView

        }

        itemClickPicker(homeRView)
        itemClickPicker(storeRView)
        itemClickPicker(sponsorRView)
        itemClickPicker(adsRView)
        itemClickPicker(billPayRView)
        itemClickPicker(brandsRView)
        itemClickPicker(donateRView)


    }

    private fun itemClickPicker(clicker: HomeRecyclerviewAdaptor) {

        clicker.itemClickListener={view: View, item: HomeRecyclerViewItem, position: Int ->
            val message = when(item){
                is HomeRecyclerViewItem.Ads -> "clicked"
                is HomeRecyclerViewItem.BillPay -> item.title
                is HomeRecyclerViewItem.Brands -> item.title
                is HomeRecyclerViewItem.Donate -> item.title
                is HomeRecyclerViewItem.Sponsor -> item.title
                is HomeRecyclerViewItem.Store -> item.title
                is HomeRecyclerViewItem.Wallet -> item.title
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java),userPreferences)

}


