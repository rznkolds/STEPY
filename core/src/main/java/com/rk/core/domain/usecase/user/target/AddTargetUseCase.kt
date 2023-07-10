package com.rk.core.domain.usecase.user.target

interface AddTargetUseCase {

    suspend operator fun invoke(target:Int)
}