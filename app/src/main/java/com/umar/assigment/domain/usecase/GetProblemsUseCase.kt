package com.umar.assigment.domain.usecase

import com.umar.assigment.core.Resource
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.domain.repository.ProblemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProblemsUseCase @Inject constructor(private val problemRepository: ProblemRepository) {
    suspend fun execute(): Flow<Resource<List<Problem>>> =
        problemRepository.getProblems()
}