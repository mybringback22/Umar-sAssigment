package com.umar.assigment.data.repository

import android.app.Application
import com.umar.assigment.core.Resource
import com.umar.assigment.core.connectivitymanager.ConnectivityObserver
import com.umar.assigment.core.connectivitymanager.NetworkConnectivityObserver
import com.umar.assigment.data.remote.mapper.toProblem
import com.umar.assigment.data.repository.datasource.LocalDataSource
import com.umar.assigment.data.repository.datasource.RemoteDataSource
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.domain.repository.ProblemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProblemRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val context: Application,
    private val connectivityObserver: ConnectivityObserver,
) : ProblemRepository {
    override suspend fun getProblems(): Flow<Resource<List<Problem>>> {
        return flow {
            if (!NetworkConnectivityObserver.isNetworkAvailable(context.applicationContext)) {
                emit(getProblemsFromDb(null))
            }
            connectivityObserver.observe()
                .collect { networkStatus ->
                    when (networkStatus) {
                        is ConnectivityObserver.Status.Available -> {
                            emit(Resource.Loading())
                            try {
                                val response = remoteDataSource.getProblem()
                                if (response.isSuccessful) {
                                    val problemsResponse = response.body()
                                    val problems = problemsResponse?.toProblem()
                                    problems?.let {
                                        localDataSource.deleteAllProblems()
                                        localDataSource.saveProblems(it)
                                    }
                                    emit(getProblemsFromDb(null))
                                } else {
                                    emit(getProblemsFromDb("Internal Error"))
                                }
                            } catch (e: Exception) {
                                emit(getProblemsFromDb(e.message))
                            }
                        }

                        is ConnectivityObserver.Status.Unavailable -> {
                        }
                    }
                }
        }
    }

    override suspend fun getProblemsFromDb(errorMessage: String?): Resource<List<Problem>> {
        val dbProblems = localDataSource.getProblems()
        if (dbProblems.isEmpty()) {
            errorMessage?.let {
                return Resource.Error(null, errorMessage)
            } ?: kotlin.run {
                return Resource.Error(null, "No Data Found")
            }
        } else {
            errorMessage?.let {
                return Resource.Error(dbProblems, errorMessage)
            } ?: kotlin.run {
                return Resource.Success(dbProblems)
            }
        }
    }
}