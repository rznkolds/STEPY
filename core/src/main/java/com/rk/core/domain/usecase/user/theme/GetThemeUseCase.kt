package com.rk.core.domain.usecase.user.theme

import kotlinx.coroutines.flow.Flow

interface GetThemeUseCase {

    operator fun invoke(): Flow<Boolean?>
}