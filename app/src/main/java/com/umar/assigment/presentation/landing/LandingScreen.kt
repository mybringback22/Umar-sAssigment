package com.umar.assigment.presentation.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umar.assigment.core.Resource
import com.umar.assigment.core.navigation.DetailScreenRoute
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.presentation.landing.viewmodel.LandingViewModel
import com.umar.assigment.presentation.landing.views.GreetingCard
import com.umar.assigment.presentation.landing.views.ProblemItem
import com.umar.assigment.ui.theme.PurpleGrey40

@Composable
fun LandingScreen(
    userName: String,
    navController: NavController,
    viewModel: LandingViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val state by viewModel.state.collectAsState()

    Scaffold(containerColor = PurpleGrey40) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GreetingCard(
                userName = userName,
                greetings = viewModel.getGreeting()
            )

            when (state) {
                is Resource.Loading -> {
                    Box(modifier = Modifier.padding(10.dp)) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }

                is Resource.Success -> {
                    val problems = (state as Resource.Success<List<Problem>>).data

                    problems?.let {
                        if (problems.isEmpty()) {
                            Text("No Data Found")
                        } else {
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .verticalScroll(scrollState)) {
                                problems.forEach { problem ->
                                    ProblemItem(problem , onClick = {prob->
                                        navController.navigate(DetailScreenRoute( prob))
                                    })
                                }
                            }
                        }

                    } ?: run {
                        Text("No Data Found")
                    }
                }

                is Resource.Error -> {
                    val problems = (state as Resource.Error<List<Problem>>).data
                    val error = (state as Resource.Error<List<Problem>>).error
                    error?.let {
                        Text(
                            "Error: ${(state as Resource.Error<List<Problem>>).error}",
                            style = TextStyle(color = Color.Red)
                        )
                    }

                    problems?.let {
                        if (problems.isEmpty()) {
                            Text("No Data Found")
                        } else {
                            Column(
                                Modifier
                                    .fillMaxHeight()
                                    .verticalScroll(scrollState)) {
                                problems.forEach { problem ->
                                    ProblemItem(problem , onClick = { prob->
                                        navController.navigate(DetailScreenRoute( prob))
                                    })
                                }
                            }
                        }

                    } ?: run {
                        Text("No Data Found")
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun LandingScreenPreview() {
    val navController = rememberNavController()
    LandingScreen("john@doe.com", navController)
}