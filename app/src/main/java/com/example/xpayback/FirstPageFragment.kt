package com.example.xpayback

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.xpayback.network.UserPreferences
import com.example.xpayback.ui.auth.VerificationPhFragment
import com.example.xpayback.ui.splash.SplashFragment2


class FirstPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_page, container, false)
        val sp = requireActivity().getSharedPreferences(SplashFragment2().splashPref,0);
        val checker=sp.getBoolean("splash",false)


        val login = requireActivity().getSharedPreferences(VerificationPhFragment().authTokenPreff,0);
        val loginChecker=login.getString("token",null)

        val userPreference = UserPreferences(requireContext())
        Handler(Looper.getMainLooper()).postDelayed({

            if (!checker){
                Navigation.findNavController(view).navigate(R.id.action_firstPageFragment_to_splashScreenFragment)
            }else{

                userPreference.accessToken.asLiveData().observe(viewLifecycleOwner){

                    if (it != null){
                        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(view)
                            .navigate(R.id.action_firstPageFragment_to_dashBoardFragmentHome)
                    }
                    else {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_firstPageFragment_to_loginPage)
                    }
                }

            }
        }, 1000)

        return view
    }

}