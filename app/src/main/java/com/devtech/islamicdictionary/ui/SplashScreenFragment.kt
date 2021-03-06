package com.devtech.islamicdictionary.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.devtech.islamicdictionary.R
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.devtech.islamicdictionary.utils.toogleActionbar


class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniToolbar()
        initAction()
    }

    private fun iniToolbar() {
        (activity as AppCompatActivity).toogleActionbar(show = false, back = false, title = "")
    }

    private fun initAction() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .getBoolean(resources.getString(R.string.key_login), false)
            ) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }, 3000L)
    }
}