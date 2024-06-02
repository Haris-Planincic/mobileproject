package com.example.accountamate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.accountamate.R
import com.example.accountamate.Screen
import com.example.accountamate.UserViewModel
import com.example.accountamate.database.MockUserDao
import com.example.accountamate.ui.theme.Green400

@Composable
fun WelcomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.Center,


        ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                colors = ButtonDefaults.buttonColors(Green400),
            )
            {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(Screen.LoginScreen.route) },
                colors = ButtonDefaults.buttonColors(Green400)
            )
            {
                Text(text = "Login")
            }
        }




    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    val userViewModel = UserViewModel(MockUserDao())
    WelcomeScreen(navController = navController)
}