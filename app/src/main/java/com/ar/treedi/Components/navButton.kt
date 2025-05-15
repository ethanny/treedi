package com.ar.treedi.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ar.treedi.ui.theme.primaryGreen

@Composable
fun NavButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 20.dp,
    buttonHeight: Dp = 40.dp,
    buttonWidth: Dp = 40.dp,
    backgroundColor: Color = Color(0x2600573F),
    iconColor: Color = primaryGreen
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(buttonHeight)
        .width(buttonWidth),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.Unspecified
        ),
        elevation = null,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(buttonHeight)
                .width(buttonWidth)
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
    }
}