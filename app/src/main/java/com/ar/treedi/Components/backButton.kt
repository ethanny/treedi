package com.ar.treedi.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import androidx.compose.foundation.border


@Composable
fun BackButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .width(55.dp)
            .height(45.dp)
            .border(
                width = 1.5.dp,
                color = Color.White,
                shape = RoundedCornerShape(25.dp)
            )
        ,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.5f),
            contentColor = Color.White
        ),
        elevation = null,
        contentPadding = PaddingValues(top = 0.dp),
    ) {
        Image(
            imageVector = Lucide.ArrowLeft,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}