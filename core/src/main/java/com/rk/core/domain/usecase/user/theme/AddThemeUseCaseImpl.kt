package com.rk.core.domain.usecase.user.theme

import com.rk.core.domain.repository.UserRepository
import com.rk.core.domain.usecase.user.target.AddTargetUseCase
import javax.inject.Inject

class AddThemeUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddThemeUseCase {

    override suspend operator fun invoke(theme: Boolean) = repo.addTheme(theme)
}