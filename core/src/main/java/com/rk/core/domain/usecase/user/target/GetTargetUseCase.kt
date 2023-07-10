package com.rk.core.domain.usecase.user.target

import kotlinx.coroutines.flow.Flow

interface GetTargetUseCase {

    operator fun invoke(): Flow<Int?>
}