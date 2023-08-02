package com.rznkolds.domain.usecase.user.language

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLanguageUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetLanguageUseCase {

    override operator fun invoke(): Flow<String?> = repo.getLanguage()
}