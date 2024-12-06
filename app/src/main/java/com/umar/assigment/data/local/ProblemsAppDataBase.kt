package com.umar.assigment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umar.assigment.core.typeconverter.ProblemTypeConverter
import com.umar.assigment.domain.model.Problem

@Database(
    entities = [Problem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProblemTypeConverter::class)
abstract class ProblemsAppDatabase : RoomDatabase() {
    abstract fun getProblemsDAO() : ProblemsDAO
}