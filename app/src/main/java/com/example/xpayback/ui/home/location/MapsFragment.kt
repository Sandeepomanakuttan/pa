package com.example.xpayback.ui.home.location

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.xpayback.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_location_edit.*
import kotlinx.android.synthetic.main.fragment_location_edit.view.*
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.fragment_maps.view.*
import kotlinx.android.synthetic.main.fragment_maps.view.address
import java.util.*


class MapsFragment : Fragment() {


    private var googleMap:GoogleMap?=null
    var currentMarker:Marker?=null
    lateinit var btnGetLocation:ImageView
    var currentLocation:Location ?= null

    private lateinit var fusedLocationProviderClient:FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        this.googleMap=googleMap
        val sydney = LatLng(currentLocation?.latitude!!, currentLocation?.longitude!!)
        drawMarker(sydney)
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener{

            override fun onMarkerDrag(p0: Marker) {


            }

            override fun onMarkerDragEnd(p0: Marker) {
               if (currentMarker != null){
                   currentMarker?.remove()

                   val newlt = LatLng(p0.position.latitude,p0.position.longitude)

                   drawMarker(newlt)
               }
            }

            override fun onMarkerDragStart(p0: Marker) {

            }
        })
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geoCoder.getFromLocation(sydney.latitude,sydney.longitude,1)
        view?.address?.text = address[0].getAddressLine(0).toString()

    }

    private fun drawMarker(sydney: LatLng) {

        val markerOption = MarkerOptions().position(sydney).title("Marker in Sydney").snippet(getAddress(sydney.latitude,sydney.longitude)).draggable(true)

        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,17f))
        currentMarker=googleMap?.addMarker(markerOption)
        currentMarker?.showInfoWindow()
    }

    private fun getAddress(latitude: Double, longitude: Double): String? {

        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geoCoder.getFromLocation(latitude,longitude,1)
        val addr = address[0].getAddressLine(0).toString()
        view?.address?.text = addr
        view?.addressedt?.setText(addr)
        return addr
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight)

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888)

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        btnGetLocation = view.getLocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        view.change_address.setOnClickListener {
            val value = view.address.text.toString()

           view.addressedt.setText(value)

            view.address.isVisible=false
            view.addressedt.isVisible=true
        }

        view.btn_confirm.setOnClickListener {

            val value = view.addressedt.text.toString()

            val bundle = bundleOf("value" to value)
            Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_locationEditFragment,bundle)
        }

        view.btn_back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_locationFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchLocation()

        btnGetLocation.setOnClickListener {

            fetchLocation()
        }


    }

    private fun fetchLocation() {

        val task =fusedLocationProviderClient.lastLocation


        if (ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED  && ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1000)
            return
        }
        task.addOnSuccessListener {location ->
            if (location != null){

                this.currentLocation=location
                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(callback)

            }
        }

        requireActivity().onBackPressedDispatcher.addCallback {

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        when(requestCode){
            1000-> if (grantResults.isNotEmpty() &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                fetchLocation()
            }
        }
    }
}