package com.rk.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.presentation.databinding.ProgressItemBinding
import com.rk.presentation.utils.StepDiffUtil
import com.rznkolds.domain.model.StepUI

class CalorieAdapter : RecyclerView.Adapter<CalorieAdapter.AdapterHolder>() {

    var onCalorieClick: (StepUI) -> Unit = {}
    private var list = ArrayList<StepUI>()
    private var peak = 0

    inner class AdapterHolder(private val binding: ProgressItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: StepUI) = with(binding) {

            progressDay.text = step.day.toString()
            progress.progress = step.calorie
            progressText.text = step.name

            progress.apply {
                max = peak
                progress = step.calorie
            }

            itemView.setOnClickListener { onCalorieClick(step) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            ProgressItemBinding.inflate(
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
        peak = new.maxBy { it -> it.calorie }.calorie
        DiffUtil.calculateDiff(StepDiffUtil(list, new)).dispatchUpdatesTo(this)
    }
}