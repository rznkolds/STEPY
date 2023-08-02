package com.rznkolds.domain.usecase.user.language

import kotlinx.coroutines.flow.Flow

interface GetLanguageUseCase {

    operator fun invoke(): Flow<String?>
}