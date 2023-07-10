package com.rk.core.domain.usecase.user.language

import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetLanguageUseCase {

    override operator fun invoke(): Flow<String?> = repo.getLanguage()
}