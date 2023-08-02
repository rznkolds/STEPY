package com.rk.presentation.ui.instructions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.rk.presentation.R
import com.rk.presentation.common.toast
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.ActivityInstructionBinding

class InstructionActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityInstructionBinding::inflate)

    @SuppressLint(
        "BatteryLife",
        "InlinedApi"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Multiple Permission

        val multiple = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->

            val notification = permission[Manifest.permission.POST_NOTIFICATIONS] ?: false
            val fitness = permission[Manifest.permission.ACTIVITY_RECOGNITION] ?: false

            if (notification && fitness) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = Uri.parse("package:${packageName}")
                    })
                }
            } else {
                toast(getString(R.string.warning))
            }
        }

        // Single Permission

        val single = registerForActivityResult(ActivityResultContracts.RequestPermission()) { access ->

            if (access) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                    startActivity(Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = Uri.parse("package:${packageName}")
                    })
                }
            }
        }

        with(binding) {

            removeRestriction.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    multiple.launch(arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                        Manifest.permission.ACTIVITY_RECOGNITION
                    ))
                } else {
                    single.launch(Manifest.permission.ACTIVITY_RECOGNITION)
                }
            }

            githubIcon.setOnClickListener {

                startActivity(Intent(Intent.ACTION_VIEW).apply {

                    data = Uri.parse("https://github.com/rznkolds")
                })
            }

            countCard.setOnClickListener {
                countLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        countOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        countOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            vibrationCard.setOnClickListener {
                vibrationLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        vibrationOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        vibrationOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            carCard.setOnClickListener {
                carLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        carOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        carOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            stepCard.setOnClickListener {
                stepLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        stepOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        stepOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            privacyCard.setOnClickListener {
                privacyLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        privacyOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        privacyOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }

            distanceCard.setOnClickListener {
                distanceLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        distanceOpenIcon.setImageResource(R.drawable.close_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    } else {
                        visibility = View.GONE
                        distanceOpenIcon.setImageResource(R.drawable.open_icon)
                        instructionScroll.apply { post { fullScroll(View.FOCUS_UP) } }
                    }
                }
            }
        }
    }
}