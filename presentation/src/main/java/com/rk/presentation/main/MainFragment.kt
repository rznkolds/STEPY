package com.rk.presentation.main

import android.Manifest.permission.ACTIVITY_RECOGNITION
import android.Manifest.permission.POST_NOTIFICATIONS
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.P
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.rk.core.common.collect
import com.rk.core.common.network
import com.rk.core.common.toast
import com.rk.core.common.viewBinding
import com.rk.core.service.StepService
import com.rk.presentation.R
import com.rk.presentation.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(requireContext())

        val host = childFragmentManager.findFragmentById(R.id.default_container) as NavHostFragment

        binding.mainBottomNav.setupWithNavController(host.findNavController())

        initObservers()
    }

    @SuppressLint(
        "ObsoleteSdkInt",
        "BatteryLife",
        "InlinedApi"
    )
    private fun initObservers() {

        // Multiple Permission

        val multiple = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->

            val notification = permission[POST_NOTIFICATIONS] ?: false
            val fitness = permission[ACTIVITY_RECOGNITION] ?: false

            if (notification && fitness) {
                if (SDK_INT >= O) {
                    requireContext().startForegroundService(
                        Intent(
                            requireContext(),
                            StepService::class.java
                        )
                    )
                } else {
                    requireContext().startService(
                        Intent(
                            requireContext(),
                            StepService::class.java
                        )
                    )
                }

                if (SDK_INT >= P) {
                    startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = Uri.parse("package:${requireContext().packageName}")
                    })
                }
            } else {
                toast(getString(R.string.warning))
            }
        }

        // Single Permission

        val single = registerForActivityResult(ActivityResultContracts.RequestPermission()) { access ->

            if (access) {
                if (SDK_INT >= O) {
                    requireContext().startForegroundService(
                        Intent(
                            requireContext(),
                            StepService::class.java
                        )
                    )
                } else {
                    requireContext().startService(
                        Intent(
                            requireContext(),
                            StepService::class.java
                        )
                    )
                }

                if (SDK_INT >= P) {
                    startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = Uri.parse("package:${requireContext().packageName}")
                    })
                }
            } else {
                toast(getString(R.string.warning))
            }
        }

        viewModel.state.collect(lifecycleScope) {

            if (it.state == null || !it.state) {
                if (SDK_INT >= TIRAMISU) {
                    multiple.launch(arrayOf(POST_NOTIFICATIONS, ACTIVITY_RECOGNITION))
                } else {
                    single.launch(ACTIVITY_RECOGNITION)
                }
            }

            it.number?.let { v -> if (v % 6 == 0 && network()) { ads() } }
        }
    }

    private fun ads() {

        val id = "ca-app-pub-3940256099942544/1033173712"

        val request = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(), id, request, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {

                println("Reklam yüklenemedi.")
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                interstitialAd.show(requireActivity())
            }
        })
    }
}