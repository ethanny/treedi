package com.ar.treedi.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.AppTypography.h3
import com.ar.treedi.ui.theme.primaryGreen
import com.composables.icons.lucide.HeartPulse
import com.composables.icons.lucide.Lucide

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
        ,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =  primaryGreen,
            contentColor = Color.Unspecified
        ),
        elevation = null,
        contentPadding = PaddingValues(top = 0.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                icon,
                modifier = Modifier
                    .height(18.dp),
                contentDescription = "icon",
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(text, style = h3)
        }
    }
}