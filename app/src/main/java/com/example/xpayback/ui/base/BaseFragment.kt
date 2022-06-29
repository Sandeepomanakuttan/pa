package com.example.xpayback.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.xpayback.network.RetrofitInstances

abstract class BaseFragment<vm : ViewModel, B :ViewBinding,R : BaseRepository> : Fragment(){

    protected lateinit var binding: B
    protected lateinit var viewModel : vm
    protected val retrofitInstances = RetrofitInstances()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater,container)

        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())

        return binding.root
    }

    abstract fun getFragmentRepository(): R

    abstract  fun getViewModel():Class<vm>

    abstract fun getFragmentBinding(inflater: LayoutInflater,container: ViewGroup?): B

}