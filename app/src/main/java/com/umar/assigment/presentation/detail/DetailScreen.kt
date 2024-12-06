package com.umar.assigment.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.presentation.landing.views.ProblemItem
import com.umar.assigment.ui.theme.PurpleGrey40

@Composable
fun DetailScreen(problem : Problem){
    Scaffold(containerColor = PurpleGrey40) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ProblemItem(problem , onClick = {})
        }
    }
}