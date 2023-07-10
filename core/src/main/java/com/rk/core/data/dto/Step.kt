package com.rk.core.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class Step (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "day_name")
    val name:String,
    val day: Int,
    val month:Int,
    val year: Int,
    val step: Int,
    val calorie: Int,
    val kilocalorie: Double,
    val meter: Int,
    val kilometer: Double,
    val target:Int,
    val state:Boolean
)