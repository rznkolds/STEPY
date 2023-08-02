package com.rznkolds.domain.usecase.user.theme

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddThemeUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddThemeUseCase {

    override suspend operator fun invoke(theme: Boolean) = repo.addTheme(theme)
}