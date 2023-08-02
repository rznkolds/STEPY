package com.rznkolds.domain.usecase.user.language

interface AddLanguageUseCase {

    suspend operator fun invoke(language: String)
}