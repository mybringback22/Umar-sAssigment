package com.umar.assigment.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.umar.assigment.core.Resource
import com.umar.assigment.data.repository.FakeProblemFailRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProblemUseCaseErrorTest {
    private lateinit var getProblemsUseCase: GetProblemsUseCase
    private lateinit var fakeProblemRepository: FakeProblemFailRepository

    @Before
    fun setup() {
        fakeProblemRepository = FakeProblemFailRepository()
        getProblemsUseCase = GetProblemsUseCase(fakeProblemRepository)
    }

    @Test
    fun `get problems from the repository failed case` () = runBlocking{
        val resource = getProblemsUseCase.execute().first()
        assertThat( resource ).isInstanceOf( Resource.Error::class.java)
        assertThat((resource as Resource.Error<*>).error ).isEqualTo("Something went Wrong")
    }
}