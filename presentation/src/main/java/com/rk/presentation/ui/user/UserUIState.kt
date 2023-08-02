package com.rk.presentation.ui.user

import com.rznkolds.domain.model.BodyUI

data class UserUIState (
    val language: String? = null,
    val theme : Boolean?=null,
    val target : Int?=null,
    val body : BodyUI?=null,
)