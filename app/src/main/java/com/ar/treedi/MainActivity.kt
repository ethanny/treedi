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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.ar.treedi.Screens.Home
import com.ar.treedi.Screens.QRScanScreen
import com.ar.treedi.Screens.TreeDetails
import com.ar.treedi.Screens.ZoomableImageScreen
import com.ar.treedi.Screens.TreeLocations
import com.ar.treedi.ui.theme.AppTypography.h1
import com.ar.treedi.ui.theme.TreediTheme
import com.ar.treedi.models.TreeData
import com.ar.treedi.ui.theme.SharedViewModel
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.ar.treedi.Screens.NoTree

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
    val isImageTapped = remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = "home",
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            EnterTransition.None
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            )
        }
    ) {
        composable("home") {
            Home(navController)
        }

        composable("treeDetail") {
            val treeData = TreeData("Rain tree", "", "", "", "", "", "","","","","", "")
//            val treeData = sharedViewModel.treeData

            Log.d("TreeDataMainAcitivty", treeData.toString())

            if (treeData != null) {
                TreeDetails(navController, treeData, isImageTapped)
            } else {
                NoTree(navController)
            }
        }

        composable("treeLocations",
        ) {
            TreeLocations(navController)
        }

        composable(
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            popExitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            route = "image_view/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.IntType })
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
            ZoomableImageScreen(navController, imageId, isImageTapped)
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