package com.rk.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.rk.presentation.R
import com.rk.presentation.common.viewBinding
import com.rk.presentation.databinding.FragmentHomeBinding
import com.rk.presentation.ui.home.adapters.CalorieAdapter
import com.rk.presentation.ui.home.adapters.KilometerAdapter
import com.rk.presentation.ui.home.adapters.StepAdapter
import com.rk.presentation.ui.home.adapters.SuccessAdapter
import com.rznkolds.domain.common.collect
import com.rznkolds.domain.model.StepUI
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val kilometerAdapter by lazy { KilometerAdapter() }
    private val successAdapter by lazy { SuccessAdapter() }
    private val calorieAdapter by lazy { CalorieAdapter() }
    private val viewModel: HomeViewModel by viewModels()
    private val stepAdapter by lazy { StepAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            stepAdapter.onStepClick = { stepTarget.text = it.step.toString() }
            calorieAdapter.onCalorieClick = { calorieTarget.text = it.kilocalorie.toString() }
            kilometerAdapter.onKilometerClick = { kilometerTarget.text = it.kilometer.toString() }

            kilometerCard.setOnClickListener {
                kilometerLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        kilometerIcon.setImageResource(R.drawable.close_icon)
                        homeScroll.apply { post { fullScroll(View.FOCUS_DOWN) } }
                    } else {
                        visibility = View.GONE
                        kilometerIcon.setImageResource(R.drawable.open_icon)
                        homeScroll.apply { post { fullScroll(View.FOCUS_DOWN) } }
                    }
                }
            }

            kalorieCard.setOnClickListener {
                caloriesLayout.apply {
                    if (visibility == View.GONE) {
                        visibility = View.VISIBLE
                        kaloriesIcon.setImageResource(R.drawable.close_icon)
                        homeScroll.apply { post { fullScroll(View.FOCUS_DOWN) } }
                    } else {
                        visibility = View.GONE
                        kaloriesIcon.setImageResource(R.drawable.open_icon)
                        homeScroll.apply { post { fullScroll(View.FOCUS_DOWN) } }
                    }
                }
            }
        }

        setup()
        initObservers()
    }


    @SuppressLint("SetTextI18n")
    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) { it ->

            it.target?.let { tempProgress.max = it }

            it.temp?.let { v ->

                v.step?.apply {
                    tempStep.text = this.toString()
                    tempProgress.progress = this
                }

                v.calorie?.let { tempKcal.text = "KCAL : $it" }
                v.kilometer?.let { tempKm.text = "KM : $it" }
                v.bmi?.let { tempBmi.text = "BMI : $it" }
            }

            if (!it.week.isNullOrEmpty()) {

                viewModel.create(ArrayList(it.week)).also { v ->
                    kilometerAdapter.setData(v)
                    successAdapter.setData(v)
                    calorieAdapter.setData(v)
                    stepAdapter.setData(v)

                    kilometerTotal.text = viewModel.sum(v, 3).toString()
                    calorieTotal.text = viewModel.sum(v, 2).toString()
                    stepTotal.text = viewModel.sum(v, 1).toString()
                }
            } else {

                val calendar = Calendar.getInstance()

                val day = calendar[Calendar.DAY_OF_MONTH]
                val month = calendar[Calendar.MONTH] + 1
                val year = calendar[Calendar.YEAR]

                val list = arrayListOf(
                    StepUI(1, viewModel.name(day, month, year), day, month, year, 0, 0, 0.0, 0, 0.0, 0, false)
                )

                viewModel.create(list).also { v ->
                    kilometerAdapter.setData(v)
                    calorieAdapter.setData(v)
                    successAdapter.setData(v)
                    stepAdapter.setData(v)
                }
            }

            achievements.adapter = successAdapter
            kilometers.adapter = kilometerAdapter
            calories.adapter = calorieAdapter
            steps.adapter = stepAdapter
        }
    }

    private fun setup() = with(binding) {

        achievements.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            justifyContent = JustifyContent.SPACE_AROUND
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.CENTER
        }

        steps.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            justifyContent = JustifyContent.SPACE_AROUND
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.CENTER
        }

        calories.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            justifyContent = JustifyContent.SPACE_AROUND
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.CENTER
        }

        kilometers.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            justifyContent = JustifyContent.SPACE_AROUND
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.CENTER
        }
    }
}