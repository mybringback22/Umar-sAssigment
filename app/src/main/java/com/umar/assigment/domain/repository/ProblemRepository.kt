package com.umar.assigment.domain.repository

import com.umar.assigment.core.Resource
import com.umar.assigment.domain.model.Problem
import kotlinx.coroutines.flow.Flow

interface ProblemRepository {
    suspend fun getProblems(): Flow<Resource<List<Problem>>>

    suspend fun getProblemsFromDb(errorMessage : String?) : Resource<List<Problem>>
}