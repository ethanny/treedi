package com.ar.treedi

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.ar.treedi.ui.theme.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val sharedViewModel: SharedViewModel = viewModel()
            TreediNav(sharedViewModel)
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
fun TreediNav(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController) // pass navController down
        }

        composable("treeDetail") {
            val treeData = sharedViewModel.treeData

            Log.d("TreeDataMainAcitivty", treeData.toString())

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
            QRScanScreen(navController = navController, sharedViewModel = sharedViewModel) {
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