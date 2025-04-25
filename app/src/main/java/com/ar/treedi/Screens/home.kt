package com.ar.treedi.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ar.treedi.ui.theme.AppTypography.h1
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.AppTypography.h3
import com.composables.icons.lucide.HeartPulse
import com.composables.icons.lucide.Lucide

@Composable
fun Home(){
    Scaffold(
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .padding(paddingValues)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFB8D1CA))
                        .padding(20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Treedi",
                            style = h1
                        )

                        Text(
                            text = "Get to know UP Cebu’s Trees in 3D!",
                            style = h3
                        )

                        Image(Lucide.HeartPulse, contentDescription = null)
                    }
                }
            }
        }
    )
}