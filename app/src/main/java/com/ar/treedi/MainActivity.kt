package com.ar.treedi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ar.treedi.Screens.Home
import com.ar.treedi.Screens.QRScanScreen
import com.ar.treedi.Screens.TreeDetails
import com.ar.treedi.Screens.TreeLocations
import com.ar.treedi.ui.theme.AppTypography.h1
import com.ar.treedi.ui.theme.TreediTheme

import com.ar.treedi.models.TreeData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreediNav()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        style = h1,
        modifier = modifier
    )
}

@Composable
fun TreediNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController) // pass navController down
        }

        composable("treeDetail") {
            val treeData = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<TreeData>("treeData")

            if (treeData != null) {
                TreeDetails(navController, treeData)
            } else {
                Text("No tree data found.") // Fallback UI
            }
        }

        composable("treeLocations") {
            TreeLocations(navController) // pass navController down
        }

        composable("qr_scan") {
            QRScanScreen(navController = navController) {
                navController.popBackStack()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TreediTheme {
        Greeting("Android")
    }
}