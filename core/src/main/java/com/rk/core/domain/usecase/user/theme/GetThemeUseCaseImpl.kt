package com.rk.core.domain.usecase.user.theme

import com.rk.core.domain.repository.UserRepository
import com.rk.core.domain.usecase.user.target.GetTargetUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetThemeUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getTheme()
}