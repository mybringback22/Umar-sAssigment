package com.umar.assigment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.umar.assigment.core.navigation.CustomNavType
import com.umar.assigment.core.navigation.DetailScreenRoute
import com.umar.assigment.core.navigation.LandingScreenRoute
import com.umar.assigment.core.navigation.LoginScreenRoute
import com.umar.assigment.domain.model.Problem
import com.umar.assigment.presentation.detail.DetailScreen
import com.umar.assigment.presentation.landing.LandingScreen
import com.umar.assigment.presentation.login.LoginScreen
import com.umar.assigment.ui.theme.UmarsAssigmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UmarsAssigmentTheme {
                val navController = rememberNavController()
                NavHost(
                    navController =  navController,
                    startDestination = LoginScreenRoute
                ) {

                    composable<LoginScreenRoute>{
                        LoginScreen(navController)
                    }

                    composable<LandingScreenRoute>{
                            backStackEntry ->
                        val param = backStackEntry.arguments?.getString("userName")
                        LandingScreen(param!! , navController)
                    }

                    composable<DetailScreenRoute>(
                        typeMap = mapOf(typeOf<Problem>() to CustomNavType.ProblemType,)
                    ){
                       val arg =  it.toRoute<DetailScreenRoute>()
                        DetailScreen(problem = arg.problem)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UmarsAssigmentTheme {
        Greeting("Android")
    }
}