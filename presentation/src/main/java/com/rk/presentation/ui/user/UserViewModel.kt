package com.rk.presentation.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.usecase.user.body.AddBodyDataUseCase
import com.rznkolds.domain.usecase.user.body.GetBodyDataUseCase
import com.rznkolds.domain.usecase.user.language.AddLanguageUseCase
import com.rznkolds.domain.usecase.user.language.GetLanguageUseCase
import com.rznkolds.domain.usecase.user.target.AddTargetUseCase
import com.rznkolds.domain.usecase.user.target.GetTargetUseCase
import com.rznkolds.domain.usecase.user.theme.AddThemeUseCase
import com.rznkolds.domain.usecase.user.theme.GetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val addLanguageUseCase: AddLanguageUseCase,
    private val addBodyDataUseCase: AddBodyDataUseCase,
    private val getBodyDataUseCase: GetBodyDataUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val addTargetUseCase: AddTargetUseCase,
    private val getTargetUseCase: GetTargetUseCase,
    private val addThemeUseCase: AddThemeUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserUIState())
    val state: StateFlow<UserUIState> = _state

    init {
        getLanguage()
        getBodyData()
        getTarget()
        getTheme()
    }

    fun addBodyData(weight: Int, height: Int) {
        viewModelScope.launch {

            addBodyDataUseCase(weight, height)
        }
    }

    private fun getBodyData() {
        getBodyDataUseCase().onEach {

            _state.value = _state.value.copy(body = it)

        }.launchIn(viewModelScope)
    }

    fun addTarget(target: Int) {
        viewModelScope.launch { addTargetUseCase(target) }
    }

    private fun getTarget() {
        getTargetUseCase().onEach {

            _state.value = _state.value.copy(target = it)

        }.launchIn(viewModelScope)
    }

    fun addTheme(theme: Boolean) {
        viewModelScope.launch { addThemeUseCase(theme) }
    }

    private fun getTheme() {
        getThemeUseCase().onEach {

            _state.value = _state.value.copy(theme = it)

        }.launchIn(viewModelScope)
    }

    fun addLanguage(language: String) {
        viewModelScope.launch {

            addLanguageUseCase(language)
        }
    }

    private fun getLanguage() {
        getLanguageUseCase().onEach {

            _state.value = _state.value.copy(language = it)

        }.launchIn(viewModelScope)
    }
}