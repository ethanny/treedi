package com.ar.treedi.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.treedi.Components.ActionButton
import com.ar.treedi.R
import com.ar.treedi.ui.theme.AppTypography.b1
import com.ar.treedi.ui.theme.AppTypography.h1
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.AppTypography.h3
import com.ar.treedi.ui.theme.accentGreen
import com.ar.treedi.ui.theme.primaryGreen
import com.composables.icons.lucide.BookOpenText
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPin
import com.composables.icons.lucide.Move3d
import com.composables.icons.lucide.ScanBarcode
import com.composables.icons.lucide.ScanLine
import com.composables.icons.lucide.ZoomIn

@Preview
@Composable
fun Home(){
    val scrollState = rememberScrollState()
    Scaffold(
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = accentGreen)
                        .padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.treedi_logo),
                        modifier = Modifier
                            .width(130.dp),
                        contentDescription = "scan",
                        contentScale = ContentScale.FillWidth
                    )

                    Text(
                        text = "Treedi",
                        style = h1
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = "Get to know UP Cebu’s Trees in 3D!",
                        style = h3
                    )

                    Spacer(Modifier.height(20.dp))

                    ActionButton("Start scanning", icon = Lucide.ScanLine, {})
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    findSection()
                    section(Lucide.ScanBarcode, "Scan", "Scan the code on the tree using your phone’s camera.", R.drawable.hand_scan)
                    section(Lucide.Move3d, "Interact", "Get up close with a lifelike 3D tree model. Rotate, zoom, and \n" +
                            "explore every detail.", R.drawable.interact)
                    section(Lucide.BookOpenText, "Learn", "Learn all about the tree’s unique features, its ecological background, and uses.", R.drawable.learn)

                    ActionButton("I'm ready to scan", icon = Lucide.ScanLine, {})
                }
            }
        }
    )
}

@Composable
fun section (
    icon: ImageVector,
    title: String,
    subtitle: String,
    image: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(icon, contentDescription = "icon", colorFilter = ColorFilter.tint(primaryGreen))
            Text(title, style = h2.copy(color = primaryGreen))
        }

        Spacer(Modifier.height(10.dp))

        Text(subtitle, style = b1.copy(textAlign = TextAlign.Center))

        Spacer(Modifier.height(20.dp))

        Image(
            painter = painterResource(id = image),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0x0D000000), shape = RoundedCornerShape(size = 10.dp)),
            contentDescription = "scan",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun findSection (
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(Lucide.ZoomIn, contentDescription = "icon", colorFilter = ColorFilter.tint(primaryGreen))
            Text("Find", style = h2.copy(color = primaryGreen))
        }

        Spacer(Modifier.height(10.dp))

        Text("Stroll around campus and spot trees with special QR tags.", style = b1.copy(textAlign = TextAlign.Center))

        Spacer(Modifier.height(20.dp))

        ActionButton("View tree locations", Lucide.MapPin, {})
    }
}