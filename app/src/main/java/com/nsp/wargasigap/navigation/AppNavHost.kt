package com.nsp.wargasigap.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nsp.wargasigap.ui.screen.AboutScreen
import com.nsp.wargasigap.ui.screen.ChatScreen
import com.nsp.wargasigap.ui.screen.EmergencyScreen
import com.nsp.wargasigap.ui.screen.GempaInfoScreen
import com.nsp.wargasigap.ui.screen.HomeScreen
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("about") { AboutScreen(navController) }
        composable("gempa_info") { GempaInfoScreen(navController) }
        composable("emergency/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            EmergencyScreen(type)
        }
        composable("chat") {
            ChatScreen(navController)
        }
    }
}