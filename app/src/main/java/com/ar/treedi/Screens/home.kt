package com.ar.treedi.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Home(){
    Scaffold(
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .width(393.dp)
                    .height(1500.dp)
                    .padding(bottom = 20.dp)
                    .padding(paddingValues)
            ) {

            }
        }
    )
}