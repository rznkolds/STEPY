package com.rk.presentation.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rk.presentation.R
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentViewPagerBinding
import com.rk.presentation.ui.onboarding.screens.first.FirstFragment
import com.rk.presentation.ui.onboarding.screens.fourth.FourthFragment
import com.rk.presentation.ui.onboarding.screens.second.SecondFragment
import com.rk.presentation.ui.onboarding.screens.third.ThirdFragment

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private val binding by viewBinding(FragmentViewPagerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            val fragments = arrayListOf(
                FirstFragment(), SecondFragment(), ThirdFragment(), FourthFragment()
            )

            pagers.isUserInputEnabled = false

            pagers.adapter = ViewPagerAdapter(
                fragments,
                requireActivity().supportFragmentManager,
                lifecycle
            )
        }
    }
}