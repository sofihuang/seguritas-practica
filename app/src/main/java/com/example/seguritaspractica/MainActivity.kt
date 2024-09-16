package com.example.seguritaspractica

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seguritaspractica.presentation.screen.DragDropScreen
import com.example.seguritaspractica.presentation.screen.ImageDetailScreen
import com.example.seguritaspractica.presentation.screen.ImageMarkerScreen
import com.example.seguritaspractica.presentation.screen.PdfScreen
import com.example.seguritaspractica.presentation.theme.SeguritasPracticaTheme
import com.example.seguritaspractica.presentation.viewmodel.ImageViewModel
import com.google.gson.Gson
import java.io.InputStream

class MainActivity : ComponentActivity() {
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private val imageViewModel: ImageViewModel by lazy { ImageViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.data?.let { uri ->
                        val inputStream: InputStream? = contentResolver.openInputStream(uri)
                        imageViewModel.loadImageFromStream(inputStream)
                    }
                }
            }

        setContent {
            SeguritasPracticaTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "drag_drop_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("drag_drop_screen") {
                            DragDropScreen(
                                onImagePick = {
                                    val intent = Intent(Intent.ACTION_PICK).apply {
                                        type = "image/*"
                                    }
                                    galleryLauncher.launch(intent)
                                },
                                imageBitmap = imageViewModel.selectedImage,
                                onRemoveImage = { imageViewModel.clearImage() },
                                onNavigateToPdf = {
                                    navController.navigate("pdf_screen")
                                },
                                onNavigateToImageMarker = {
                                    navController.navigate("image_marker_screen")
                                }
                            )
                        }
                        composable("pdf_screen") {
                            PdfScreen()
                        }
                        composable("image_marker_screen") {
                            ImageMarkerScreen(navController = navController)
                        }
                        composable("image_detail/{escenarioJson}") { backStackEntry ->
                            val escenarioJson = backStackEntry.arguments?.getString("escenarioJson") ?: ""
                            ImageDetailScreen(escenarioJson = escenarioJson)
                        }
                    }
                }
            }
        }
    }
}
