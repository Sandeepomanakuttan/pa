package com.example.xpayback.ui.home

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.xpayback.R
import com.example.xpayback.databinding.FragmentQrScannerBinding
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_qr_scanner.*
import java.util.jar.Manifest

class QrScannerFragment : BaseFragment<AuthViewModel,FragmentQrScannerBinding,AuthRepository>() {

    private lateinit var codeScanner: CodeScanner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentQrScannerBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val status:Boolean? = arguments?.getBoolean("status")


        if (status == true) {
            btn_cancel.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_qrScannerFragment_to_homeFragment)
            }
        }else{
            btn_cancel.isVisible=false
        }
        var flash = false
        btn_flash.setOnClickListener {
            if (flash) {
                flash=false
                codeScanner.isFlashEnabled = flash
            }else{
                codeScanner.isFlashEnabled = true
                flash=true

            }
        }

        btn_galary.setOnClickListener {
            openGallery()
        }
        requireActivity().onBackPressedDispatcher.addCallback {


        }


        setPermission()
        codeScaner()

    }

    private fun openGallery() {

        val img = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(img, 1002)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1002 && resultCode== Activity.RESULT_OK){
            //codeScanner= data?.data


        }
    }

    private fun codeScaner() {
        val scannerView = binding.scannerView
        val activity = requireActivity()

        codeScanner = CodeScanner(requireActivity(),scannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK

            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE

            scanMode =ScanMode.CONTINUOUS

            isAutoFocusEnabled = true

            isFlashEnabled = false

            codeScanner.decodeCallback = DecodeCallback {

                activity.runOnUiThread{

                    Toast.makeText(activity, it.text, Toast.LENGTH_SHORT).show()
                }
            }

            errorCallback = ErrorCallback {
                Toast.makeText(activity, "Camera initialization error", Toast.LENGTH_SHORT).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()

    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    fun setPermission(){

        val permission = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),101)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            101 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(), "this app need ur camera permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java),userPreferences)
}