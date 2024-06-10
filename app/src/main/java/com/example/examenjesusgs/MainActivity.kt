package com.example.examenjesusgs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examenjesusgs.ui.MyViewModel
import com.example.examenjesusgs.ui.Pantalla1
import com.example.examenjesusgs.ui.Pantalla2
import com.example.examenjesusgs.ui.theme.ExamenJesusGSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenJesusGSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

enum class PrincipalScreen(@StringRes val title: Int) {
    Pantalla1(title = R.string.p1),
    Pantalla2(title = R.string.p2),
}

@Composable
fun Principal(navController: NavHostController = rememberNavController()) {
    val viewModel: MyViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    NavHost(
        navController = navController,
        startDestination = PrincipalScreen.Pantalla1.name,
    ) {
        composable(route = PrincipalScreen.Pantalla1.name) {
	    //navController.navigate(PrincipalScreen.Pantalla1.name)
		//navController.navigate(PrincipalScreen.Pantalla2.name)
            Pantalla1(viewModel,uiState) { navController.navigate(PrincipalScreen.Pantalla2.name) }
        }
        composable(route = PrincipalScreen.Pantalla2.name) {
            Pantalla2(viewModel,uiState) { navController.navigate(PrincipalScreen.Pantalla1.name) }
        }
    }
}

