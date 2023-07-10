package com.rk.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.rk.core.data.dto.Step

@Dao
interface StepDao {

    // Get and add step method for dao of the room

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStep(step: Step)

    @Query("SELECT * FROM steps WHERE day=:day AND month=:month AND year=:year")
    fun getStep(day: Int, month: Int, year: Int): Step

    // Get week steps method for dao of the room

    @Query("SELECT * FROM ( SELECT * FROM steps ORDER BY id DESC LIMIT 7 ) sub ORDER BY id ASC")
    fun getWeekSteps(): List<Step>

    // Get month steps method for dao of the room

    @Query("SELECT * FROM ( SELECT * FROM steps ORDER BY id DESC LIMIT 30 ) sub ORDER BY id ASC")
    fun getMonthSteps(): List<Step>
}