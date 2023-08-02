package com.rznkolds.domain.usecase.user.target

interface AddTargetUseCase {

    suspend operator fun invoke(target:Int)
}