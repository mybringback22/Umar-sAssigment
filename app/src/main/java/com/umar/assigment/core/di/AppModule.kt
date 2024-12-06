package com.umar.assigment.core.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.umar.assigment.core.connectivitymanager.ConnectivityObserver
import com.umar.assigment.core.connectivitymanager.NetworkConnectivityObserver
import com.umar.assigment.data.local.ProblemsAppDatabase
import com.umar.assigment.data.local.ProblemsDAO
import com.umar.assigment.data.remote.ApiConfig
import com.umar.assigment.data.remote.ApiService
import com.umar.assigment.data.repository.ProblemRepositoryImpl
import com.umar.assigment.data.repository.datasource.LocalDataSource
import com.umar.assigment.data.repository.datasource.RemoteDataSource
import com.umar.assigment.data.repository.datasourceimpl.LocalDataSourceImpl
import com.umar.assigment.data.repository.datasourceimpl.RemoteDataSourceImpl
import com.umar.assigment.domain.repository.ProblemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor {
            val requestBuilder = it.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            it.proceed(requestBuilder.build())
        }.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideMyApi( okHttpClient: OkHttpClient) : ApiService {
        return  Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGetTodoDAO(problemsAppDatabase: ProblemsAppDatabase):ProblemsDAO{
        return problemsAppDatabase.getProblemsDAO()
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application): ProblemsAppDatabase {
        return Room.databaseBuilder(app, ProblemsAppDatabase::class.java, "my_problem_app_db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(problemDAO: ProblemsDAO) : LocalDataSource {
        return LocalDataSourceImpl(problemDAO)
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(app: Application): ConnectivityObserver {
        return NetworkConnectivityObserver(app)
    }

    @Singleton
    @Provides
    fun provideRepository( remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, context : Application, connectivityObserver: ConnectivityObserver) : ProblemRepository{
        return ProblemRepositoryImpl(remoteDataSource, localDataSource, context , connectivityObserver)
    }

}