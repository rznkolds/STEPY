package com.rk.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.core.data.dto.Step

class StepDiffUtil(
    private val oldList: ArrayList<Step>,
    private val newList: ArrayList<Step>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].id == newList[new].id)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}