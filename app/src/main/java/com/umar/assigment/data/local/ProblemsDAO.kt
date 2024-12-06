package com.umar.assigment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umar.assigment.domain.model.Problem

@Dao
interface ProblemsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProblems( problems : List<Problem> )

    @Query("SELECT * FROM table_problem")
    suspend fun getAllProblems(): List<Problem>

    @Query("DELETE FROM table_problem")
    suspend fun deleteAll()
}