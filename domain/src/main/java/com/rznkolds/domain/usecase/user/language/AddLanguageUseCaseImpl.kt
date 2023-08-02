package com.rznkolds.domain.usecase.user.language

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddLanguageUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddLanguageUseCase {

    override suspend operator fun invoke(language: String) = repo.addLanguage(language)
}