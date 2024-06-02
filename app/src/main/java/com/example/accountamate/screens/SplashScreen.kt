package com.example.accountamate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.accountamate.R
import com.example.accountamate.Screen
import com.example.accountamate.UserViewModel
import com.example.accountamate.database.MockUserDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        scope.launch {
            delay(3000)  // wait for 3 seconds
            navController.navigate(Screen.WelcomeScreen.route)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.splashscreen),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "App Logo"
        )
    }
}

@Composable
@Preview
fun SpScreenPreview() {
    val navController = rememberNavController()
    val userViewModel = UserViewModel(MockUserDao())
    SplashScreen(navController = navController)
}
