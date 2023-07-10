package com.rk.presentation.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.core.data.dto.Step
import com.rk.presentation.databinding.ProgressItemBinding
import com.rk.presentation.utils.StepDiffUtil

class KilometerAdapter : RecyclerView.Adapter<KilometerAdapter.AdapterHolder>() {

    var onKilometerClick: (Step) -> Unit = {}
    private var list = ArrayList<Step>()
    private var peak = 0

    inner class AdapterHolder(private val binding: ProgressItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(step: Step) = with(binding) {

            progressDay.text = step.day.toString()
            progressText.text = step.name

            progress.apply {
                max = peak
                progress = step.meter
            }

            itemView.setOnClickListener { onKilometerClick(step) }
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

    override fun getItemCount(): Int = 7

    fun setData(new: ArrayList<Step>) {
        list.clear()
        list.addAll(new)
        peak = new.maxBy {it.meter }.meter
        DiffUtil.calculateDiff(StepDiffUtil(list, new)).dispatchUpdatesTo(this)
    }
}