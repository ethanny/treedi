package com.ar.treedi.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import io.github.sceneview.Scene
import io.github.sceneview.animation.Transition.animateRotation
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.ModelNode
import io.github.sceneview.node.Node
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNode
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@Composable
fun TreeDetails(navController: NavController, treeData: TreeData, isImageTapped: MutableState<Boolean>) {
    val systemUiController = rememberSystemUiController()
    val isFullImageMode = remember { mutableStateOf(false) }

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = accentGreen
        )
        onDispose {}
    }

    val scrollState = rememberScrollState()

    fun getDisplayImage(id: String): Int {
        return when (id) {
            "Flame tree" -> R.drawable.flame
            "Rain tree" -> R.drawable.rain
            "Golden shower tree" -> R.drawable.goldenshower
            "Tambis" -> R.drawable.tambis
            "Ylang-ylang" -> R.drawable.ylang
            else -> R.drawable.flame
        }
    }

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
                    .padding(vertical = 10.dp, horizontal = 20.dp),
            ) {
                IconButton(Lucide.ArrowLeft, { navController.popBackStack() })
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    IconButton(Lucide.ScanEye, { isFullImageMode.value = !isFullImageMode.value })
                }
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = accentGreen)
                        .fillMaxWidth()
                        .animateContentSize()
                        .then(
                            if (isFullImageMode.value) {
                                Modifier.fillMaxHeight()
                            } else {
                                Modifier
                                    .height(200.dp)
                            }
                        )
                ) {


                    if(treeData.nativeName == "Kalachuchi") {
                        if(isFullImageMode.value) {
                            Text("Rotate, pinch to zoom in or out to explore tree details",
                                style = h2.copy(color = primaryGreen, textAlign = TextAlign.Center),
                                modifier = Modifier.padding(20.dp),

                                )
                        }

                        if (!isImageTapped.value)
                            modelScene(isFullImageMode)
                    }else {
                    Image(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        painter = painterResource(id = getDisplayImage(treeData.nativeName)),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    ) }
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

                            ) {
                                getImagesForId(treeData.nativeName).forEach { image ->
                                    val width = (LocalConfiguration.current.screenWidthDp.dp - (if (getImagesForId(treeData.nativeName).count() > 3) 70.dp else 50.dp)) / 3.dp
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .clickable {
                                                isImageTapped.value = true
                                                navController.navigate("image_view/${image}")
                                            }
                                    ) {
                                        Image(
                                            modifier = Modifier.height(150.dp).width(width.dp),
                                            painter = painterResource(id = image),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillHeight,
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
fun ZoomableImageScreen(navController: NavController, imageResourceId: Int, isImageTapped: MutableState<Boolean>) {
    val systemUiController = rememberSystemUiController()
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val minScale = 1f
    val maxScale = 3f
    
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(color = Color.Black)
        onDispose {
            isImageTapped.value = false
            systemUiController.setStatusBarColor(color = accentGreen)
        }
    }

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(vertical = 10.dp, horizontal = 16.dp)
            ) {
                IconButton(
                    icon = Lucide.ArrowLeft,
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                )
                
                // Title can be added here if needed
            }
        },
        containerColor = Color.Black,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Black)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = { tapOffset ->
                                scale = if (scale > minScale * 1.2f) minScale else maxScale
                                offset = if (scale <= minScale * 1.2f) Offset.Zero else {
                                    // Center the zoom on the tap location
                                    val centeredOffset = Offset(
                                        (size.width / 2 - tapOffset.x) * (scale - 1),
                                        (size.height / 2 - tapOffset.y) * (scale - 1)
                                    )
                                    // Manually constrain the offset within bounds
                                    val maxX = size.width * (scale - 1)
                                    val maxY = size.height * (scale - 1)
                                    Offset(
                                        centeredOffset.x.coerceIn(-maxX, maxX),
                                        centeredOffset.y.coerceIn(-maxY, maxY)
                                    )
                                }
                            }
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            translationX = offset.x,
                            translationY = offset.y
                        )
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                scale = (scale * zoom).coerceIn(minScale, maxScale)
                                
                                // Only allow panning when zoomed in
                                if (scale > 1f) {
                                    val maxX = size.width * (scale - 1) / 2
                                    val maxY = size.height * (scale - 1) / 2
                                    
                                    val newOffset = Offset(
                                        offset.x + pan.x * scale,
                                        offset.y + pan.y * scale
                                    )
                                    
                                    offset = Offset(
                                        newOffset.x.coerceIn(-maxX, maxX),
                                        newOffset.y.coerceIn(-maxY, maxY)
                                    )
                                } else {
                                    offset = Offset.Zero
                                }
                            }
                        }
                )
            }
        }
    )
}

@Composable
fun modelScene(isFullImageMode: MutableState<Boolean>){
    Box(modifier = Modifier.fillMaxSize()) {
        val engine = rememberEngine()
        val modelLoader = rememberModelLoader(engine)

        val centerNode = rememberNode(engine)

        val cameraNode = rememberCameraNode(engine) {
            position = Position(x = 0f, y = 0f, z = 1f)
            centerNode.addChildNode(this)
        }

        val cameraTransition = rememberInfiniteTransition(label = "CameraTransition")
        val cameraRotation by cameraTransition.animateRotation(
            initialValue = Rotation(y = 0.0f),
            targetValue = Rotation(y = if (isFullImageMode.value) 0.0f else 360.0f),

            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 7.seconds.toInt(DurationUnit.MILLISECONDS))
            )
        )

        Scene(
        modifier = Modifier.fillMaxSize().align(Alignment.Center),
        engine = engine,
        modelLoader = modelLoader,
        cameraNode = cameraNode,
            isOpaque = false,
        cameraManipulator = rememberCameraManipulator(
            orbitHomePosition = cameraNode.worldPosition,
            targetPosition = centerNode.worldPosition
        ),
            childNodes = listOf(centerNode,
                rememberNode {
                    val visualCenter = Node(engine).apply {
                        position = Position(y = -0.25f) // offset to center model
                        addChildNode(
                            ModelNode(
                                modelInstance = modelLoader.createModelInstance("models/Kalachuchi.glb"),
                                scaleToUnits = 0.8f,
                                centerOrigin = Position(x = 0f, y = 0f, z = 0f)
                            )
                        )
                    }
                    visualCenter
                }),
        onFrame = {
            centerNode.rotation = cameraRotation
            cameraNode.lookAt(centerNode)
        },
    )
    }
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
    val isImageTapped = remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val treeData = TreeData("Flame tree", "Delonix regia", "", "", "", "", "","","","","", "")
    MaterialTheme {
        Surface {
            TreeDetails(navController, treeData, isImageTapped)
        }
    }
}
