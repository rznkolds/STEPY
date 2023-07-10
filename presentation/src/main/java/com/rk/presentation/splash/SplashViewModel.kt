package com.rk.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.domain.usecase.user.ads.AddCountAdUseCase
import com.rk.core.domain.usecase.user.language.GetLanguageUseCase
import com.rk.core.domain.usecase.user.onboarding.GetOnboardingUseCase
import com.rk.core.domain.usecase.user.theme.GetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getOnboardingUseCase: GetOnboardingUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SplashUIState())
    val state: StateFlow<SplashUIState> = _state

    init {
        getLanguage()
        getTheme()
    }

    fun getOnboarding() {

        getOnboardingUseCase().onEach {

            if (it != null) {
                _state.value = _state.value.copy(onboarding = it)
            } else {
                _state.value = _state.value.copy(onboarding = false)
            }

        }.launchIn(viewModelScope)
    }

    private fun getTheme() {

        getThemeUseCase().onEach {

            _state.value = _state.value.copy(theme = it)

        }.launchIn(viewModelScope)
    }

    private fun getLanguage() {

        getLanguageUseCase().onEach {

            _state.value = _state.value.copy(language = it)

        }.launchIn(viewModelScope)
    }
}