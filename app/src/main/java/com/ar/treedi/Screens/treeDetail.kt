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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.treedi.Components.IconButton
import com.ar.treedi.R
import com.ar.treedi.ui.theme.AppTypography.b1
import com.ar.treedi.ui.theme.AppTypography.h2
import com.ar.treedi.ui.theme.accentGreen
import com.ar.treedi.ui.theme.brown
import com.ar.treedi.ui.theme.lightGray
import com.ar.treedi.ui.theme.primaryGreen
import com.ar.treedi.ui.theme.red
import com.ar.treedi.ui.theme.yellow
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Earth
import com.composables.icons.lucide.Leaf
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MapPinHouse
import com.composables.icons.lucide.ScanEye
import com.composables.icons.lucide.Trees

@Preview
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
                    Text("Ecological background", style = h2.copy(color = primaryGreen))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        EcologicalCard(
                            icon = Lucide.MapPinHouse,
                            iconColor = red,
                            title = "Native Location",
                            description = "Madagascar",
                            modifier = Modifier.weight(1f)
                        )
                        EcologicalCard(
                            icon = Lucide.MapPinHouse,
                            iconColor = yellow,
                            title = "Climate",
                            description = "Tropical and Subtropical",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        EcologicalCard(Lucide.Earth, brown, "Systemic", "Terrestrial", modifier = Modifier.weight(1f))
                        EcologicalCard(Lucide.Trees, primaryGreen, "Habitat Type", "Forest", modifier = Modifier.weight(1f))
                    }
                    EcologicalCard(Lucide.Leaf, primaryGreen, "Endemicity", "Cultivated, not naturalized", modifier = Modifier.fillMaxWidth())
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text("Uses", style = h2.copy(color = primaryGreen))
                    Text("The splendid blooms of royal poinciana have a function beyond mere beauty. The seeds of this showy tree can be roasted to create a strong, coffee-like oil. This essence is employed in crafting scented cosmetic products. Further, the tree shelters diverse insects, with butterflies being noteworthy residents. A striking use involves the tree's bark, which yields a sticky gum, useful as a glue and in paint manufacturing.", style = b1)
                }
            }
        }
    )
}

@Composable
fun EcologicalCard(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(size = 5.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                icon,
                contentDescription = "icon",
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier.size(14.dp)
            )
            Text(title, style = b1.copy(fontWeight = FontWeight.Medium, color = Color.Gray))
        }
        Text(description, style = b1)
    }
}
