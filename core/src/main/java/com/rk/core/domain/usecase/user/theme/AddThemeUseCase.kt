package com.rk.core.domain.usecase.user.theme

interface AddThemeUseCase {

    suspend operator fun invoke(theme:Boolean)
}