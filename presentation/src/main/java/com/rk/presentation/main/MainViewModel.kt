package com.rk.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.domain.usecase.service.GetStateServiceUseCase
import com.rk.core.domain.usecase.user.ads.AddCountAdUseCase
import com.rk.core.domain.usecase.user.ads.GetCountAdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addCountAdUseCase: AddCountAdUseCase,
    private val getCountAdUseCase: GetCountAdUseCase,
    private val getStateServiceUseCase: GetStateServiceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state

    init {
        addCountAd()
        getCountAd()
        getStateService()
    }

    private fun addCountAd() { viewModelScope.launch { addCountAdUseCase() } }

    private fun getStateService() {

        getStateServiceUseCase().onEach {

            _state.value = _state.value.copy(state = it)

        }.launchIn(viewModelScope)
    }

    private fun getCountAd() {

        getCountAdUseCase().onEach {

            _state.value = _state.value.copy(number = it)

        }.launchIn(viewModelScope)
    }
}