package com.rk.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rk.core.data.dto.Step
import com.rk.core.data.source.local.StepDao

@Database(entities = [Step::class] , version = 1 )
abstract class StepDatabase : RoomDatabase() {

    abstract fun dao () : StepDao
}