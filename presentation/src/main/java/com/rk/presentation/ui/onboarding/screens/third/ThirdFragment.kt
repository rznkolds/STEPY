package com.rk.presentation.ui.onboarding.screens.third

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rk.presentation.R
import com.rk.presentation.common.next
import com.rk.presentation.common.toast
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment(R.layout.fragment_third) {

    private val binding by viewBinding(FragmentThirdBinding::bind)
    private val viewModel: ThirdViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            thirdNextButton.setOnClickListener {

                val target = targetEdittext.text

                if (!target.isNullOrEmpty()) {

                    viewModel.addTarget(
                        target.toString().toInt()
                    )

                    next(R.id.pagers, 3)
                } else {
                    toast(getString(R.string.blanks))
                }
            }
        }
    }
}