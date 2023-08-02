package com.rk.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.presentation.databinding.AchievementsItemBinding
import com.rk.presentation.utils.StepDiffUtil
import com.rznkolds.domain.model.StepUI

class SuccessAdapter : RecyclerView.Adapter<SuccessAdapter.AdapterHolder>() {

    var onCalorieClick: (StepUI) -> Unit = {}
    private var list = ArrayList<StepUI>()
    private var max = 0

    inner class AdapterHolder(private val binding: AchievementsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: StepUI) = with(binding) {

            if (step.state) { achievementIcon.visibility = View.VISIBLE }

            achievementProgress.progress = step.step
            achievementProgress.max = step.target
            achievementText.text = step.name

            itemView.setOnClickListener { onCalorieClick(step) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            AchievementsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(new: ArrayList<StepUI>) {
        list.clear()
        list.addAll(new)
        max = new.maxBy { it -> it.calorie }.calorie
        DiffUtil.calculateDiff(StepDiffUtil(list, new)).dispatchUpdatesTo(this)
    }
}