package com.umar.assigment.data.repository.datasourceimpl

import com.umar.assigment.data.remote.ApiService
import com.umar.assigment.data.remote.model.ProblemsDTO
import com.umar.assigment.data.repository.datasource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor( private val apiService: ApiService ) : RemoteDataSource {
    override suspend fun getProblem(): Response<ProblemsDTO> {
        return apiService.getProblems()
    }
}