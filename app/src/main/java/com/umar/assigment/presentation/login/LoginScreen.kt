package com.umar.assigment.presentation.login


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umar.assigment.core.navigation.LandingScreenRoute
import com.umar.assigment.ui.theme.PurpleGrey40
import com.umar.assigment.ui.theme.Typography

@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(containerColor = PurpleGrey40) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Welcome", style = Typography.bodyLarge.copy(fontSize = 24.sp, color = Color.White)
            )
            Text(
                "This is Umar's Application", style = Typography.bodyLarge.copy(color = Color.White)
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(width = 1.dp, shape = RoundedCornerShape(4.dp), color = Color.White)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Login", style = Typography.bodyLarge.copy(color = Color.White)
                )

                Box(modifier = Modifier.height(10.dp))

                TextField(value = email,
                    onValueChange = { newText ->
                        email = newText
                        error = null
                    },
                    label = { Text("Email") },
                    placeholder = { Text("john@doe.com") })

                Box(modifier = Modifier.height(10.dp))

                TextField(value = password,
                    onValueChange = { newText ->
                        password = newText
                        error = null
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text("Password") },
                    placeholder = { Text("*******") })

                Box(modifier = Modifier.height(10.dp))

                error?.let {
                    Text(
                        text = it,
                        color = Color.Red, // Change color to red for visibility
                        style = Typography.bodySmall.copy(fontSize = 12.sp),
                        modifier = Modifier.padding(vertical = 4.dp) // Add some padding around the error text
                    )
                }


                Button(
                    onClick = {
                        error = if (password.isBlank() || email.isBlank()) {
                            "Please fill all the fields"
                        } else {
                            navController.navigate(LandingScreenRoute(email))
                            null

                        }
                    }, modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text("Login")
                }
            }
        }

    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}