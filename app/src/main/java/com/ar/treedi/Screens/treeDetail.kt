package com.ar.treedi.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TreeDetails(navController: NavController, treeData: TreeData) {
    val systemUiController = rememberSystemUiController()
    val isFullImageMode = remember { mutableStateOf(false) }
    
    // Animation for ContentScale
    val contentScale by animateFloatAsState(
        targetValue = if (isFullImageMode.value) 0f else 1f,
        animationSpec = tween(300),
        label = "contentScale"
    )

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = accentGreen
        )
        onDispose {}
    }

    val scrollState = rememberScrollState()

    fun getImagesForId(id: String): List<Int> {
        return when (id) {
            "Flame tree" -> listOf(R.drawable.delonix, R.drawable.delonix2, R.drawable.delonix3)
            "Kalachuchi" -> listOf(R.drawable.plumeria, R.drawable.plumeria2, R.drawable.plumeria3, R.drawable.plumeria4, R.drawable.plumeria5, R.drawable.plumeria6, R.drawable.plumeria7)
            "Rain tree" -> listOf(R.drawable.samanea, R.drawable.samanea2, R.drawable.samanea3, R.drawable.samanea4, R.drawable.samanea5, R.drawable.samanea6)
            "Golden shower tree" -> listOf(R.drawable.cassia, R.drawable.cassia2, R.drawable.cassia3, R.drawable.cassia4, R.drawable.cassia5, R.drawable.cassia6)
            "Tambis" -> listOf(R.drawable.syzygium, R.drawable.syzygium2, R.drawable.syzygium3, R.drawable.syzygium4, R.drawable.syzygium5, R.drawable.syzygium6, R.drawable.syzygium7)
            "Ylang-ylang" -> listOf(R.drawable.cananga, R.drawable.cananga2, R.drawable.cananga3, R.drawable.cananga4)
            else -> listOf(R.drawable.delonix)
        }
    }

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = accentGreen)
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            ) {
                IconButton(Lucide.ArrowLeft, { navController.popBackStack() })
                IconButton(Lucide.ScanEye, { isFullImageMode.value = !isFullImageMode.value })
            }
        },
        containerColor = Color.White,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(Color.White)
                    .then(
                        if (!isFullImageMode.value) {
                            Modifier.verticalScroll(scrollState)
                        } else {
                            Modifier
                        }
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .background(color = accentGreen)
                        .fillMaxWidth()
                        .animateContentSize()
                        .then(
                            if (isFullImageMode.value) {
                                Modifier.fillMaxHeight()
                            } else {
                                Modifier
                            }
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.interact),
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxSize(),
                        contentDescription = "Tree",
                        contentScale =  ContentScale.FillWidth
                    )
                }

                AnimatedVisibility(
                    visible = !isFullImageMode.value,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
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

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                                ,

                            ) {
                                getImagesForId(treeData.nativeName).forEach { image ->
                                    Box(
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(115.dp)
                                            .clip(RoundedCornerShape(10.dp)) // clip the image to rounded corners
                                    ) {
                                        Image(
                                            painter = painterResource(id = image), // Replace with your image
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                        )
                                    }
                                }
                            }
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

@Preview(showBackground = true)
@Composable
fun TreeDetailScreenPreview() {
    val navController = rememberNavController()
    val treeData = TreeData("Kalachuchi", "", "", "", "", "", "","","","","", "")
    MaterialTheme {
        Surface {
            TreeDetails(navController, treeData)
        }
    }
}
