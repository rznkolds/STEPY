package com.rznkolds.domain.usecase.user.theme

interface AddThemeUseCase {

    suspend operator fun invoke(theme:Boolean)
}