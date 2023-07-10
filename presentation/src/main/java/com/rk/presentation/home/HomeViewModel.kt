package com.rk.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.common.Resource
import com.rk.core.data.dto.Step
import com.rk.core.domain.usecase.steps.temp.GetTempDataUseCase
import com.rk.core.domain.usecase.steps.week.GetWeekStepsUseCase
import com.rk.core.domain.usecase.user.target.GetTargetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.time.Month
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeekStepsUseCase: GetWeekStepsUseCase,
    private val getTempDataUseCase: GetTempDataUseCase,
    private val getTargetUseCase: GetTargetUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        getWeekSteps()
        getTempData()
        getTarget()
    }

    private fun getTarget() {

        getTargetUseCase().onEach {

            _state.value = _state.value.copy(target = it)

        }.launchIn(viewModelScope)
    }

    private fun getTempData() {

        getTempDataUseCase().onEach {

            _state.value = _state.value.copy(temp = it)

        }.launchIn(viewModelScope)
    }

    private fun getWeekSteps() {

        getWeekStepsUseCase().onEach { v ->
            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {
                    v.data.let { _state.value = _state.value.copy(week = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun create(temp: ArrayList<Step>): ArrayList<Step> {

        while (temp.size < 7) {

            val last = temp.last()

            val february = last.month == (Month.FEBRUARY).value
            val january = last.month == (Month.JANUARY).value

            when (last.day) {

                28 -> {
                    if (february && leap(last.year)) {
                        temp.add(last.id, 29, last.month, last.year)
                    } else if (february) {
                        temp.add(last.id, 1, last.month + 1, last.year)
                    } else {
                        temp.add(last.id, 29, last.month, last.year)
                    }
                }

                29 -> {
                    if (february) {
                        temp.add(last.id, 1, last.month + 1, last.year)
                    } else {
                        temp.add(last.id, 30, last.month, last.year)
                    }
                }

                30 -> {
                    if (month(last.month)) {
                        temp.add(last.id, 31, last.month, last.year)
                    } else {
                        temp.add(last.id, 1, last.month + 1, last.year)
                    }
                }

                31 -> {
                    if (january) {
                        temp.add(last.id, 1, 1, last.year + 1)
                    } else {
                        temp.add(last.id, 1, last.month + 1, last.year)
                    }
                }

                else -> { temp.add(last.id, last.day + 1, last.month, last.year) }
            }
        }

        return temp
    }

    private fun month(month: Int): Boolean = month in listOf(1, 3, 5, 7, 8, 10, 12)

    private fun leap(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

    private fun ArrayList<Step>.add(id: Int, day: Int, month: Int, year: Int) {
        add(
            Step(id + 1, name(day, month, year), day, month, year, 0, 0, 0.0, 0, 0.0, 0, false)
        )
    }

    fun name(day: Int, month: Int, year: Int): String {

        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.MONTH, month - 1)
            set(Calendar.YEAR, year)
        }

        return SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
    }

    fun sum(list:ArrayList<Step>,mod:Int): Int {
        var total = 0
        for (i in list) {
            total += when (mod) {
                1 -> i.step
                2 -> i.calorie
                else -> i.meter
            }
        }
        return total
    }
}