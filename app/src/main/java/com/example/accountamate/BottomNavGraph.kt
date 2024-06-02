package com.example.accountamate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.example.bottomnavbardemo.screens.MoodTracker
import com.example.accountamate.screens.Notes
import com.example.accountamate.screens.Mate




@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.MoodTracker.route
    ) {
        composable(route = BottomNav.MoodTracker.route) {
            CalenderScreen()
        }
        composable(route = BottomNav.Mate.route) {
            Mate()
        }
        composable(route = BottomNav.Notes.route) {
            Notes()
        }
    }
}