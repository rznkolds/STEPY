package com.rk.presentation.ui.onboarding.screens.fourth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rk.presentation.R
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentFourthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthFragment : Fragment(R.layout.fragment_fourth) {

    private val binding by viewBinding(FragmentFourthBinding::bind)

    private val viewModel: FourthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            forthFinishButton.setOnClickListener {

                viewModel.addOnboarding(true)

                findNavController().navigate(R.id.action_view_pager_to_main)
            }
        }
    }
}