package com.rk.presentation.onboarding.screens.third

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rk.core.common.next
import com.rk.core.common.toast
import com.rk.core.common.viewBinding
import com.rk.presentation.R
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
                } else {
                    toast(getString(R.string.blanks))
                }

                next(R.id.pagers, 3)
            }
        }
    }
}