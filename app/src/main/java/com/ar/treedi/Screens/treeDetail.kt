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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ar.treedi.Components.IconButton
import com.ar.treedi.R
import com.ar.treedi.ui.theme.AppTypography.b1
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.accentGreen
import com.ar.treedi.ui.theme.lightGray
import com.ar.treedi.ui.theme.primaryGreen
import com.ar.treedi.ui.theme.red
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ScanEye

@Composable
fun TreeDetails() {
    val scrollState = rememberScrollState()
    Scaffold(
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(bottom = 20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .background(color = accentGreen)
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        IconButton(Lucide.ArrowLeft, {})
                        IconButton(Lucide.ScanEye, {})
                    }

                    Image(
                        painter = painterResource(id = R.drawable.interact),
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentDescription = "Tree",
                        contentScale = ContentScale.FillWidth
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text("Flame Tree", style = h2.copy(color = primaryGreen))
                    Text("Delonix regia", style = b1.copy(color = primaryGreen))

                    Spacer(Modifier.height(20.dp))

                    Text("This tree is noted for its fern-like leaves and flamboyant display of orange-red flowers over summer. In many tropical parts of the world it is grown as an ornamental tree.", style = b1)
                    Text("Also known as: Peacock flower, royal poinciana, flamboyant, phoenix flower, flame of the forest", style = b1.copy(color = Color.Gray))
                    Text("Family:", style = b1.copy(color = Color.Gray))
                    Text("Genus:", style = b1.copy(color = Color.Gray))

                    Spacer(Modifier.height(20.dp))

                    Text("Images", style = h2.copy(color = primaryGreen))
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .background(lightGray)
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text("Ecological Background", style = h2.copy(color = primaryGreen))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White,  shape = RoundedCornerShape(size = 5.dp))
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    ) {
                        Row() {
                            Image(Lucide.ArrowLeft, contentDescription = "icon", colorFilter = ColorFilter.tint(
                                red))
                            Text("Enedemicity", style = b1.copy(fontWeight = FontWeight.Medium, color = Color.Gray))
                        }
                        Text("Cultivated, not naturalized", style = b1)
                    }
                }
            }
        }
    )
}