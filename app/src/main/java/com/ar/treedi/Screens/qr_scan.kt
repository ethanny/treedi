package com.ar.treedi.Screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.ar.treedi.Components.QROverlay
import com.composables.icons.lucide.ArrowLeft
import kotlinx.coroutines.delay
import com.composables.icons.lucide.Lucide
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

import com.ar.treedi.network.fetchTreeData
import androidx.navigation.NavController
import com.ar.treedi.Components.IconButton
import com.ar.treedi.ui.theme.AppTypography.b1
import com.ar.treedi.ui.theme.AppTypography.b2
import com.ar.treedi.ui.theme.AppTypography.b3
import com.ar.treedi.ui.theme.AppTypography.h3
import com.ar.treedi.ui.theme.accentGreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@Composable
fun QRScanScreen(navController: NavController, onBackPressed: () -> Unit) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Color.Black
        )
        onDispose {}
    }

    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // Always show overlay
    val showOverlay = true
    var isFetching by remember { mutableStateOf(false)}
    var hasCameraPermission by remember { mutableStateOf(false) }
    var cameraError by remember { mutableStateOf<String?>(null) }

    // Check for camera permission
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
    }

    // Request camera permission if not already granted
    LaunchedEffect(Unit) {
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            hasCameraPermission = true
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    fun handleQrCode(code: String) {
        if (isFetching) return
        isFetching = true

        Log.d("QRScanScreen0", "QR Code detected: $code")

        coroutineScope.launch {
            try {
                val treeData = fetchTreeData(code)

                navController.currentBackStackEntry?.savedStateHandle?.set("treeData", treeData)
                navController.navigate("treeDetail")
            } catch (e: Exception) {
                Log.e("QRScanScreen", "Error fetching tree data", e)
                isFetching = false
            }
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (hasCameraPermission) {
                AndroidView(
                    factory = { ctx ->
                        val previewView = PreviewView(ctx).apply {
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            scaleType = PreviewView.ScaleType.FILL_CENTER
                        }

                        val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

                        cameraProviderFuture.addListener({
                            try {
                                val cameraProvider = cameraProviderFuture.get()

                                // Unbind any previous use cases
                                cameraProvider.unbindAll()

                                val preview = Preview.Builder()
                                    .build()
                                    .also {
                                        it.setSurfaceProvider(previewView.surfaceProvider)
                                    }

                                val barcodeScanner = BarcodeScanning.getClient()

                                val imageAnalysis = ImageAnalysis.Builder()
                                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                    .build()

                                imageAnalysis.setAnalyzer(
                                    ContextCompat.getMainExecutor(ctx)
                                ) { imageProxy ->
                                    val mediaImage = imageProxy.image
                                    if (mediaImage != null) {
                                        val image = InputImage.fromMediaImage(
                                            mediaImage,
                                            imageProxy.imageInfo.rotationDegrees
                                        )

                                        barcodeScanner.process(image)
                                            .addOnSuccessListener { barcodes ->
                                                for (barcode in barcodes) {
                                                    if (barcode.valueType == Barcode.TYPE_URL ||
                                                        barcode.valueType == Barcode.TYPE_TEXT) {
                                                        barcode.rawValue?.let { code ->
                                                            handleQrCode(code)
                                                        }
                                                    }
                                                }
                                            }
                                            .addOnFailureListener { e ->
                                                Log.e("QRScanScreen", "Barcode scanning failed", e)
                                            }
                                            .addOnCompleteListener {
                                                imageProxy.close()
                                            }
                                    } else {
                                        imageProxy.close()
                                    }
                                }

                                try {
                                    // Select back camera
                                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                                    // Bind use cases to camera
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner,
                                        cameraSelector,
                                        preview,
                                        imageAnalysis
                                    )

                                    Log.d("QRScanScreen", "Camera setup successful")
                                } catch (e: Exception) {
                                    Log.e("QRScanScreen", "Camera binding failed", e)
                                    cameraError = "Failed to bind camera: ${e.message}"
                                }
                            } catch (e: Exception) {
                                Log.e("QRScanScreen", "Camera provider error", e)
                                cameraError = "Camera error: ${e.message}"
                            }
                        }, ContextCompat.getMainExecutor(ctx))

                        previewView
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Camera permission required",
                        style = h3.copy(color = Color.White),
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Error message
            cameraError?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                        .background(Color(0x88000000)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        style = h3.copy(color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Box(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                IconButton(
                    Lucide.ArrowLeft,
                    { navController.popBackStack() },
                    bgColor = Color.White.copy(alpha = 0.5f),
                    iconColor = Color.White
                )
            }

            if (showOverlay) {
                // Instruction text above the QR overlay
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Box(
                        modifier = Modifier
                            .width(250.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White.copy(alpha = 0.25f))
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)

                    ) {
                        Text("Point your camera at a QR code",modifier = Modifier.fillMaxWidth(), style = b2.copy(color = Color.White, textAlign = TextAlign.Center)
                        )
                    }

                    QROverlay(
                        modifier = Modifier
                    )
                }
            }
        }
    }
}