package com.rk.presentation.ui.onboarding.screens.second

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rk.presentation.R
import com.rk.presentation.common.next
import com.rk.presentation.common.toast
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding by viewBinding(FragmentSecondBinding::bind)
    private val viewModel: SecondViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            secondNextBtn.setOnClickListener {

                val weight = secondWeightEdittext.text

                val height = secondSizeEdittext.text

                if (!weight.isNullOrEmpty() && !height.isNullOrEmpty()) {

                    viewModel.addBmi(
                        weight.toString().toInt(),
                        height.toString().toInt()
                    )

                    next(R.id.pagers, 2)
                } else {
                    toast(getString(R.string.blanks))
                }
            }
        }
    }
}