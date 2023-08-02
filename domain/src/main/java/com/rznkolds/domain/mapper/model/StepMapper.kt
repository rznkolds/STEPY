package com.rznkolds.domain.mapper.model

interface StepMapper<I, O> {

    fun map(input: I?): O
}