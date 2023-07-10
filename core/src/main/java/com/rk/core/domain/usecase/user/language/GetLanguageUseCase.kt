package com.rk.core.domain.usecase.user.language

import com.rk.core.data.dto.Body
import kotlinx.coroutines.flow.Flow

interface GetLanguageUseCase {

    operator fun invoke(): Flow<String?>
}