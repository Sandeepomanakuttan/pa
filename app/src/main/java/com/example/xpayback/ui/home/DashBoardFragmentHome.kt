package com.example.xpayback.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.MenuBottomAdaptor
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentDashBoardHomeBinding
import com.example.xpayback.network.Api
import com.example.xpayback.network.UserApi
import com.example.xpayback.ui.DashBoardHomeMainFragment
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.ui.home.profile.UserRepository
import com.example.xpayback.ui.home.profile.UserViewModel


class DashBoardFragmentHome : BaseFragment<UserViewModel,FragmentDashBoardHomeBinding,UserRepository>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )= FragmentDashBoardHomeBinding.inflate(inflater,container,false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.bottomNavigation.background = null
        val viewPager = binding.viewPager

        val bundle = bundleOf("status" to true)


        binding.navProfile.setOnClickListener {
           Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment,bundle)
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }

        binding.navNotification.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_notificationFragment,bundle)
        }
        var menuAdaptor =
            MenuBottomAdaptor(intFragment()[0], requireActivity().supportFragmentManager, lifecycle)

        viewPager.adapter = menuAdaptor


        binding.fab.setOnClickListener {

            val c= true.also { binding.bottomNavigation.menu.getItem(2).isChecked = it }

            if (c) {
                binding.header.isVisible = true
                menuAdaptor = MenuBottomAdaptor(intFragment()[2],
                    requireActivity().supportFragmentManager,
                    lifecycle)

                viewPager.adapter = menuAdaptor
            }
        }


        binding.bottomNavigation.setOnItemSelectedListener {


            when (it.itemId) {

                R.id.home -> {
                    binding.header.isVisible = true
                    menuAdaptor = MenuBottomAdaptor(
                        intFragment()[0],
                        requireActivity().supportFragmentManager,
                        lifecycle
                    )

                    viewPager.adapter = menuAdaptor
                }
                R.id.storeFragment -> {
                    binding.header.isVisible = true
                    menuAdaptor = MenuBottomAdaptor(
                        intFragment()[1],
                        requireActivity().supportFragmentManager,
                        lifecycle
                    )

                    viewPager.adapter = menuAdaptor
                }

                R.id.purchases -> {
                    binding.header.isVisible = true
                    menuAdaptor = MenuBottomAdaptor(
                        intFragment()[3],
                        requireActivity().supportFragmentManager,
                        lifecycle
                    )

                    viewPager.adapter = menuAdaptor
                }

                R.id.whitelist -> {
                    binding.header.isVisible = true
                    menuAdaptor = MenuBottomAdaptor(
                        intFragment()[4],
                        requireActivity().supportFragmentManager,
                        lifecycle
                    )

                    viewPager.adapter = menuAdaptor
                }
            }

            true
        }




    }

    private fun intFragment(): ArrayList<Fragment> {
        return arrayListOf(
            DashBoardHomeMainFragment(),
            StoreFragment(),
            QrScannerFragment(),
            PurchasesFragment(),
            WatchListFragment()
        )
    }

    override fun getFragmentRepository() =
        UserRepository(retrofitInstances.buildApi(UserApi::class.java))

    override fun getViewModel() = UserViewModel::class.java





}