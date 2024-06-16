package com.iscoding.qrcode.graph

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.iscoding.qrcode.generatecode.GenerateQRCode
import com.iscoding.qrcode.generatecode.MainScreen
import com.iscoding.qrcode.scancode.AskFromCameraOrStorageScreen
import com.iscoding.qrcode.scancode.fromcamera.ScanCodeScreen
import com.iscoding.qrcode.scancode.fromstorage.presentation.ShowAllImagesScreen
import com.iscoding.qrcode.scancode.fromstorage.presentation.ShowQRCodeImageData

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen
    ) {
        composable(route = Screens.MainScreen) {
           MainScreen(navController = navController)

        }
        composable(route = Screens.ScanCode) {
            ScanCodeScreen()

        }
        composable(route = Screens.GenerateCode) {
            GenerateQRCode()
        }
        composable(route = Screens.ShowAllImagesScreen) {
           ShowAllImagesScreen(navController = navController)
        }
        composable(route = Screens.GenerateCode) {
            GenerateQRCode()
        }
        composable(route = Screens.AskFromCameraOrStorageScreen) {
           AskFromCameraOrStorageScreen(navController = navController)
        }

        composable(
            route = "${Screens.ShowQRCodeDataScreen}/{qrCodeData}/{imageUri}",
            arguments = listOf(
                navArgument("qrCodeData") { type = NavType.StringType },
                navArgument("imageUri") { type = NavType.StringType }
            )
        ) { backStackEntry ->
//            val qrCodeData = backStackEntry.arguments?.getString("qrCodeData") ?: ""
//            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
            val qrCodeData = requireNotNull(backStackEntry.arguments).getString("qrCodeData")
            val imageUri = requireNotNull(backStackEntry.arguments).getString("imageUri")
            // Pass qrCodeData and imageUri to your ShowQRCodeImageData composable
            ShowQRCodeImageData(qrCodeData = qrCodeData!!, imageUri = imageUri!!)
        }
    }
}