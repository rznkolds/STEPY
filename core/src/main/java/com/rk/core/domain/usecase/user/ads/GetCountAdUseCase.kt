package com.rk.core.domain.usecase.user.ads

import kotlinx.coroutines.flow.Flow

interface GetCountAdUseCase {

    operator fun invoke(): Flow<Int?>
}