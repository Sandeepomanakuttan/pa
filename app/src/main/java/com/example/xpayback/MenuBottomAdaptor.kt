package com.example.xpayback

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MenuBottomAdaptor(private val fragments: Fragment, fragmentManager: FragmentManager, life:Lifecycle) : FragmentStateAdapter(fragmentManager,life){
    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return fragments
    }

}