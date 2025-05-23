package com.ar.treedi.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.treedi.ui.theme.primaryGreen
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    bgColor: Color = Color(0x2600573F),
    iconColor: Color = primaryGreen
) {
    Button(
        onClick = onClick,
        elevation = null,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(width = 1.25.dp, if (iconColor == Color.White) iconColor else Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor =  bgColor,
            contentColor = Color.Unspecified
        ),
    ) {
        Image(
            icon,
            modifier = Modifier
                .height(18.dp),
            contentDescription = "icon",
            colorFilter = ColorFilter.tint(iconColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconButtonPreview() {
    IconButton(
        icon = Lucide.ArrowLeft,
        onClick = { }
    )
}