package com.umar.assigment.data.repository

import com.umar.assigment.core.Resource
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.domain.repository.ProblemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeProblemRepository : ProblemRepository {

    val problems = mutableListOf<Problem>()

    override suspend fun getProblems(): Flow<Resource<List<Problem>>> {
        return flow { emit(Resource.Success(problems)) }
    }

    override suspend fun getProblemsFromDb(errorMessage: String?): Resource<List<Problem>> {
        return Resource.Success(problems)
    }
}