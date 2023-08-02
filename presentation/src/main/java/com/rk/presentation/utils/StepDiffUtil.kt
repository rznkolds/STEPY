package com.rk.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.rznkolds.domain.model.StepUI

class StepDiffUtil(
    private val oldList: ArrayList<StepUI>,
    private val newList: ArrayList<StepUI>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].id == newList[new].id)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}