package com.umar.assigment.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.umar.assigment.core.Resource
import com.umar.assigment.data.repository.FakeProblemRepository
import com.umar.assigment.domain.model.AssociatedDrug
import com.umar.assigment.domain.model.Problem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProblemsUseCaseSuccessTest{
    private lateinit var getProblemsUseCase: GetProblemsUseCase
    private lateinit var fakeProblemRepository: FakeProblemRepository

    @Before
    fun setup(){
        fakeProblemRepository = FakeProblemRepository()
        getProblemsUseCase = GetProblemsUseCase(fakeProblemRepository)

        val associatedDrugs = mutableListOf<AssociatedDrug>()
        val associatedDrug = AssociatedDrug()
        associatedDrug.id = "AssociatedDrug#1"
        associatedDrug.dose = "2"
        associatedDrug.name = "Asprin"
        associatedDrug.strength= "600 mg"
        associatedDrugs.add(associatedDrug)
        val associatedDrugTwo = AssociatedDrug()
        associatedDrugTwo.id = "AssociatedDrug#2"
        associatedDrugTwo.dose = "3"
        associatedDrugTwo.name = "Telenol"
        associatedDrugTwo.strength= "600 mg"
        associatedDrugs.add(associatedDrugTwo)
        fakeProblemRepository.problems.add(Problem(id = 0, problemName = "Asthma", className = "ClassName2" , associatedDrugs = associatedDrugs))
        fakeProblemRepository.problems.add(Problem(id = 0, problemName = "Diabaties"))
    }

    @Test
    fun `get problems from the repository correctly` () = runBlocking{
        val resource = getProblemsUseCase.execute().first()
        assertThat( resource ).isInstanceOf( Resource.Success::class.java)
        assertThat((resource as Resource.Success<*>).data ).isNotNull()
    }

}