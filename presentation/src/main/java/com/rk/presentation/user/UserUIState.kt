package com.rk.presentation.user

import com.rk.core.data.dto.Body

data class UserUIState (
    val language: String? = null,
    val theme : Boolean?=null,
    val target : Int?=null,
    val body : Body?=null,
)