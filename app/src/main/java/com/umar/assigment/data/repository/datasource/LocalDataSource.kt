package com.umar.assigment.data.repository.datasource

import com.umar.assigment.domain.model.Problem

interface LocalDataSource {
    suspend fun getProblems() : List<Problem>
    suspend fun saveProblems( problems : List<Problem>)
    suspend fun deleteAllProblems()
}