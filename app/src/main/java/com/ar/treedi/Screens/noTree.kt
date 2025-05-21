package com.ar.treedi.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.treedi.Components.IconButton
import com.ar.treedi.ui.theme.AppTypography.h1
import com.ar.treedi.ui.theme.accentGreen
import com.ar.treedi.ui.theme.primaryGreen
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessageCircleWarning

@Composable
fun NoTree(navController: NavController){
    Scaffold(
        topBar = {
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFB8D1CA))
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(vertical = 10.dp, horizontal = 20.dp),
            ) {
                IconButton(Lucide.ArrowLeft, { navController.popBackStack() })
            }
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(accentGreen)
                .offset(y = -50.dp)
        ) {
            Image(
                Lucide.MessageCircleWarning,
                modifier = Modifier
                    .height(64.dp),
                contentScale = ContentScale.FillHeight,
                contentDescription = "icon",
                colorFilter = ColorFilter.tint(primaryGreen)
            )
            Spacer(Modifier.height(20.dp))
            Text("No tree data found.", style = h1.copy(color = primaryGreen))
        }
    }
}