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
import androidx.navigation.NavController
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

import com.ar.treedi.models.TreeData

@Composable
fun TreeDetails(navController: NavController, treeData: TreeData) {
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
                        IconButton(Lucide.ArrowLeft, { navController.popBackStack() })
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
                    Text(treeData.nativeName, style = h2.copy(color = primaryGreen))
                    Text(treeData.scientificName, style = b1.copy(color = primaryGreen))

                    Spacer(Modifier.height(20.dp))

                    Text(treeData.description, style = b1)
                    Text("Also known as: ${treeData.otherNames}", style = b1.copy(color = Color.Gray))
                    Text("Family: ${treeData.family}", style = b1.copy(color = Color.Gray))
                    Text("Genus: ${treeData.genus}", style = b1.copy(color = Color.Gray))

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
                            description = treeData.nativeLocation,
                            modifier = Modifier.weight(1f)
                        )
                        EcologicalCard(
                            icon = Lucide.MapPinHouse,
                            iconColor = yellow,
                            title = "Climate",
                            description = treeData.climate,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        EcologicalCard(Lucide.Earth, brown, "Systemic", treeData.system, modifier = Modifier.weight(1f))
                        EcologicalCard(Lucide.Trees, primaryGreen, "Habitat Type", treeData.habitatType, modifier = Modifier.weight(1f))
                    }
                    EcologicalCard(Lucide.Leaf, primaryGreen, "Endemicity", treeData.endemicity, modifier = Modifier.fillMaxWidth())
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text("Uses", style = h2.copy(color = primaryGreen))
                    Text(treeData.uses, style = b1)
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
