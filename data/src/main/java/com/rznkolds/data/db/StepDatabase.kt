package com.rznkolds.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rznkolds.data.dto.Step
import com.rznkolds.data.source.room.StepDao

@Database(entities = [Step::class] , version = 1 )
abstract class StepDatabase : RoomDatabase() {

    abstract fun dao () : StepDao
}