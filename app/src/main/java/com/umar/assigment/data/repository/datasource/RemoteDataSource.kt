package com.umar.assigment.data.repository.datasource

import com.umar.assigment.data.remote.model.ProblemsDTO
import retrofit2.Response

interface RemoteDataSource  {
    suspend fun getProblem() : Response<ProblemsDTO>
}