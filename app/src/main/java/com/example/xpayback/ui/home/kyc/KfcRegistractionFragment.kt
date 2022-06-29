package com.example.xpayback.ui.home.kyc

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.camera_popup.*
import kotlinx.android.synthetic.main.fragment_kfc_registraction.*
import kotlinx.android.synthetic.main.fragment_kfc_registraction.view.*
import java.io.File


class KfcRegistractionFragment : Fragment() {

    private var mFrontUri: Uri?=null
    private var mBackUri: Uri?=null
    private lateinit var startForFrontImageResult: ActivityResultLauncher<Intent>
    private lateinit var startForBackImageResult: ActivityResultLauncher<Intent>
    private var file: File? = null
    var button: Button? = null
    var imageView: ImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kfc_registraction, container, false)

       // val layout=view.findViewById(R.id.design_bottom_sheet) as LinearLayout

        val type = resources.getStringArray(R.array.proof_type)
        val page=arguments?.getBoolean("page")
        var idType=arguments?.getString("id_type")

        imageView=view.findViewById(R.id.id_front)


        view.btnBack.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_kfcRegistractionFragment_to_kycFragment)
        }

        view.spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?,position : Int, p3: Long) {
                idType=type[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }

        if (page==true){
            view.idLayout.isVisible=true
        }

        check(view.btnSubmit)





        val bottomSheetDialog =  BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.buttom_sheet)

        bottomSheetDialog.show()

         startForFrontImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                when (resultCode) {
                    Activity.RESULT_OK -> {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data!!

                        mFrontUri = fileUri
                        id_front.setImageURI(fileUri)
                        check(view.btnSubmit)
                    }
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        startForBackImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                when (resultCode) {
                    Activity.RESULT_OK -> {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data!!

                        mBackUri = fileUri
                        id_proof_bk.setImageURI(fileUri)
                        check(view.btnSubmit)
                    }
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        val cameraPopupDialog =  BottomSheetDialog(requireContext())
        cameraPopupDialog.setContentView(R.layout.camera_popup)

        view.ivFrd_Pic.setOnClickListener {

            val camera =cameraPopupDialog.take_pic

            camera.setOnClickListener {
                takePhoto("front")
                cameraPopupDialog.dismiss()

            }

            cameraPopupDialog.iv_gallary.setOnClickListener {
                selectImageInAlbum("front")
                cameraPopupDialog.dismiss()

            }

            val cancel = cameraPopupDialog.cancel

            cancel.setOnClickListener {
                cameraPopupDialog.dismiss()
            }

            cameraPopupDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            cameraPopupDialog.show()

        }


        view.back_pic.setOnClickListener {


            val camera = cameraPopupDialog.take_pic

            camera.setOnClickListener {
                takePhoto("back")
                check(view.btnSubmit)
                cameraPopupDialog.dismiss()
            }

            cameraPopupDialog.iv_gallary.setOnClickListener {
                selectImageInAlbum("back")
                cameraPopupDialog.dismiss()

            }

            val cancel = cameraPopupDialog.cancel

            cancel.setOnClickListener {
                cameraPopupDialog.dismiss()
            }

            cameraPopupDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            cameraPopupDialog.show()

        }



        view.btnSubmit.setOnClickListener {

            var bundle:Bundle?=null
            if (page==true) {
                bundle = bundleOf("wallet" to true)
            }
            Navigation.findNavController(view).navigate(R.id.action_kfcRegistractionFragment_to_updateuccefullyFragment,bundle)
        }


        return view
    }

    private fun check(btnSubmit: Button?) {

        if (mFrontUri == null || mBackUri == null) {
            btnSubmit?.isEnabled = false
            btnSubmit?.alpha = 0.5f
        } else {
            btnSubmit?.isEnabled = true
            btnSubmit?.alpha=1f
        }
    }



    private fun selectImageInAlbum(type: String) {

        if (type=="front"){
            ImagePicker.with(this)
                .galleryOnly()	//User can only capture image using Camera
                .crop(16f,9f)
                .createIntent { intent ->
                    startForFrontImageResult.launch(intent)
                }
        }else{
            ImagePicker.with(this)
                .galleryOnly()	//User can only capture image using Camera
                .crop(16f,9f)
                .createIntent { intent ->
                    startForBackImageResult.launch(intent)
                }
        }


    }
    private fun takePhoto(type: String?) {

        if (type == "front") {
            ImagePicker.with(this)
                .cameraOnly()    //User can only capture image using Camera
                .crop()
                .createIntent { intent ->
                    startForFrontImageResult.launch(intent)
                }
        }else{
            ImagePicker.with(this)
                .cameraOnly()	//User can only capture image using Camera
                .crop(16f,9f)
                .createIntent { intent ->
                    startForBackImageResult.launch(intent)
                }
        }
    }

    }





