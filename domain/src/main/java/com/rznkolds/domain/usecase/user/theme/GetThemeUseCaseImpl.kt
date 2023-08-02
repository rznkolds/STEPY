package com.rznkolds.domain.usecase.user.theme

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemeUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetThemeUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getTheme()
}