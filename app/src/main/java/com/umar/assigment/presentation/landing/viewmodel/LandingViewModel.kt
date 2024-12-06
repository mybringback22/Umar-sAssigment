package com.umar.assigment.presentation.landing.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umar.assigment.core.Resource
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.domain.usecase.GetProblemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(private val useCase: GetProblemsUseCase) : ViewModel() {
    private val _state: MutableStateFlow<Resource<List<Problem>>> =
        MutableStateFlow(Resource.Loading())
    val state: StateFlow<Resource<List<Problem>>> = _state


    init {
        viewModelScope.launch {
            fetchProblems()
        }
    }


    fun getGreeting(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (currentHour) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }

    suspend fun fetchProblems() {
        _state.value = Resource.Loading()
        viewModelScope.launch {
            useCase.execute().collect { value ->
                _state.value = value
            }
        }
    }
}