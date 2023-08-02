package com.rk.presentation.ui.onboarding.screens.first

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rk.presentation.R
import com.rk.presentation.common.next
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            firstNextBtn.setOnClickListener { next(R.id.pagers,1) }
        }
    }
}