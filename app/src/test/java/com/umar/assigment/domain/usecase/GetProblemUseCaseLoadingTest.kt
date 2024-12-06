package com.umar.assigment.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.umar.assigment.core.Resource
import com.umar.assigment.data.repository.FakeProblemLoadingRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProblemUseCaseLoadingTest {
    private lateinit var getProblemsUseCase: GetProblemsUseCase
    private lateinit var fakeProblemRepository: FakeProblemLoadingRepository

    @Before
    fun setup() {
        fakeProblemRepository = FakeProblemLoadingRepository()
        getProblemsUseCase = GetProblemsUseCase(fakeProblemRepository)
    }

    @Test
    fun `get problems from the repository Loading case` () = runBlocking{
        val resource = getProblemsUseCase.execute().first()
        assertThat( resource ).isInstanceOf( Resource.Loading::class.java)

    }
}