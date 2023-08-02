package com.rk.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.presentation.databinding.ProgressItemBinding
import com.rk.presentation.utils.StepDiffUtil
import com.rznkolds.domain.model.StepUI

class StepAdapter : RecyclerView.Adapter<StepAdapter.AdapterHolder>() {

    private var list = ArrayList<StepUI>()
    var onStepClick: (StepUI) -> Unit = {}

    inner class AdapterHolder(private val binding: ProgressItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: StepUI) = with(binding) {

            progressDay.text = step.day.toString()

            progressText.text = step.name

            progress.apply {
                max = step.target
                progress = step.step
            }


            itemView.setOnClickListener { onStepClick(step) }
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
        DiffUtil.calculateDiff(StepDiffUtil(list, new)).dispatchUpdatesTo(this)
    }
}