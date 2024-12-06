package com.umar.assigment.data.repository.datasourceimpl

import com.umar.assigment.data.local.ProblemsDAO
import com.umar.assigment.data.remote.ApiService
import com.umar.assigment.data.repository.datasource.LocalDataSource
import com.umar.assigment.domain.model.Problem
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val problemsDAO: ProblemsDAO) : LocalDataSource {
    override suspend fun getProblems(): List<Problem> {
        return problemsDAO.getAllProblems()
    }

    override suspend fun saveProblems(problems: List<Problem>) {
        problemsDAO.insertAllProblems(problems)
    }

    override suspend fun deleteAllProblems() {
        problemsDAO.deleteAll()
    }
}