package com.rk.core.domain.usecase.user.language

import com.rk.core.domain.repository.UserRepository
import com.rk.core.domain.usecase.user.body.AddBodyDataUseCase
import javax.inject.Inject

class AddLanguageUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddLanguageUseCase {

    override suspend operator fun invoke(language: String) = repo.addLanguage(language)
}