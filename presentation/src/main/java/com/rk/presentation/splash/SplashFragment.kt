package com.rk.presentation.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rk.core.common.collect
import com.rk.presentation.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var local: Locale

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        local = Locale.getDefault()

        object : CountDownTimer(2000L, 1000L) {

            override fun onFinish() { viewModel.getOnboarding() }

            override fun onTick(millisUntilFinished: Long) { }
        }.start()

        initObserver()
    }

    private fun initObserver() {

        viewModel.state.collect(lifecycleScope) {

            it.onboarding?.let { v ->
                if (v) {
                    findNavController().navigate(R.id.action_splash_to_main)
                } else {
                    findNavController().navigate(R.id.action_splash_to_view_pager)
                }
            }

            it.theme?.let { v ->
                if (v) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                }
            }

            if (it.language != null) {
                if (it.language.isNotEmpty()) { setLanguage(it.language) }
            } else {
                when(local.language) {
                    "tr" -> setLanguage("tr-TR")
                    "en" -> setLanguage("en-EN")
                    "de" -> setLanguage("de-DE")
                    "it" -> setLanguage("it-IT")
                    "es" -> setLanguage("es-ES")
                    "fr" -> setLanguage("fr-FR")
                    else -> setLanguage("en-EN")
                }
            }
        }
    }

    private fun setLanguage(code :String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(code))
    }
}