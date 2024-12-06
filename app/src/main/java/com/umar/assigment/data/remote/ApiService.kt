package com.umar.assigment.data.remote

import com.umar.assigment.data.remote.model.ProblemsDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(ApiConfig.GET_PROBLEMS)
    suspend fun getProblems() : Response<ProblemsDTO>
}