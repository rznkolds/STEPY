package com.rk.presentation.user

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.rk.core.common.collect
import com.rk.core.common.network
import com.rk.core.common.toast
import com.rk.core.common.viewBinding
import com.rk.presentation.R
import com.rk.presentation.databinding.FragmentUserBinding
import com.rk.presentation.instructions.InstructionActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private val binding by viewBinding(FragmentUserBinding::bind)
    private val viewModel: UserViewModel by viewModels()
    private lateinit var locale: Locale

    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locale = Locale.getDefault()

        MobileAds.initialize(requireContext())

        with(binding) {

            if (network()) { banner.loadAd(AdRequest.Builder().build()) }

            instructionsCard.setOnClickListener {
                startActivity(Intent(requireContext(),InstructionActivity::class.java))
            }

            bodyCard.setOnClickListener {
                updateBodyLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        bodyIcon.setImageResource(R.drawable.close_icon)
                        userScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        bodyIcon.setImageResource(R.drawable.open_icon)
                        userScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            updateBodyBtn.setOnClickListener {
                val weight = updateWeightEditxt.text
                val height = updateHeightEditxt.text

                if (!weight.isNullOrEmpty() && !height.isNullOrEmpty()) {

                    viewModel.addBodyData(
                        weight.toString().toInt(),
                        height.toString().toInt()
                    )
                } else {
                    toast(getString(R.string.blanks))
                }
            }

            updateTargetBtn.setOnClickListener {

                val target = updateTargetEditxt.text

                if (!target.isNullOrEmpty()) {

                    viewModel.addTarget(
                        target.toString().toInt()
                    )
                } else {
                    toast(getString(R.string.blanks))
                }
            }

            if (mode()) { themeSwitch.isChecked = true }

            themeSwitch.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    viewModel.addTheme(true)
                } else {
                    viewModel.addTheme(false)
                }
            }

            linkedln.setOnClickListener { open("https://www.linkedin.com/in/rznkolds") }

            github.setOnClickListener { open("https://github.com/rznkolds") }

            shareCard.setOnClickListener { }

            languageText.setOnClickListener { it.menu() }

            feedbackCard.setOnClickListener { send() }

            language.setOnClickListener { it.menu() }

            initObservers()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) { it ->

            it.body?.let {
                updateHeightEditxt.setText(it.height.toString())
                updateWeightEditxt.setText(it.weight.toString())
            }

            it.target?.let { updateTargetEditxt.setText(it.toString()) }

            if (it.language != null) {

                if (it.language.isNotEmpty()) {

                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(it.language))

                    when(it.language) {
                        "tr-TR" -> language.text = "TR"
                        "en-EN" -> language.text = "EN"
                        "de-DE" -> language.text = "DE"
                        "it-IT" -> language.text = "IT"
                        "es-ES" -> language.text = "ES"
                        "fr-FR" -> language.text = "FR"
                        else -> language.text = "EN"
                    }
                }
            } else {
                when(locale.language) {
                    "tr" -> language.text = "TR"
                    "en" -> language.text = "EN"
                    "de" -> language.text = "DE"
                    "it" -> language.text = "IT"
                    "es" -> language.text = "ES"
                    "fr" -> language.text = "FR"
                    else -> language.text = "EN"
                }
            }

            it.theme?.let { v ->
                if (v) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                }
            }
        }
    }

    private fun send() {
        startActivity(Intent(Intent.ACTION_SENDTO).apply {

            data = Uri.parse("mailto:rznkolds@outlook.com")
        })
    }

    private fun open(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {

            data = Uri.parse(url)
        })
    }

    private fun View.menu() {

        val menu = PopupMenu(requireContext(), this).apply {
            inflate(R.menu.external_menu)
            show()
        }

        menu.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.turkish -> viewModel.addLanguage("tr-TR")
                R.id.english -> viewModel.addLanguage("en-EN")
                R.id.germany -> viewModel.addLanguage("de-DE")
                R.id.italian -> viewModel.addLanguage("it-IT")
                R.id.spanish -> viewModel.addLanguage("es-ES")
                R.id.french  -> viewModel.addLanguage("fr-FR")
            }

            false
        }
    }

    private fun mode(): Boolean {
        val mode = requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return mode == Configuration.UI_MODE_NIGHT_YES
    }
}