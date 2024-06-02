package com.example.accountamate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.accountamate.BottomNav
import com.example.accountamate.R
import com.example.accountamate.Screen
import com.example.accountamate.UserViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.accountamate.database.MockUserDao
import com.example.accountamate.ui.theme.Brown400
import com.example.accountamate.ui.theme.Brown500
import com.example.accountamate.ui.theme.Brown800
import com.example.accountamate.ui.theme.Green400
import com.example.accountamate.ui.theme.Green600


@Composable
fun LoginScreen(navController: NavHostController, viewModel: UserViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }



    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green400)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = R.drawable.accountalogo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 32.dp)
        )
        Text(
            text = "Log In",
            fontSize = 32.sp,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            color = Brown800,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Username:",
            modifier = Modifier.align(Alignment.Start),
            color = Brown400
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green600,
                unfocusedBorderColor = Green400
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Password:",
            modifier = Modifier.align(Alignment.Start),
            color = Brown400
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green600,
                unfocusedBorderColor = Green400
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(Green400),
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    viewModel.viewModelScope.launch {
                        snackbarHostState.showSnackbar("Username or password cannot be empty")
                    }
                } else {
                    viewModel.viewModelScope.launch {
                        val loginSuccessful = viewModel.login(username, password)
                        if (loginSuccessful) {
                            navController.navigate(BottomNav.MoodTracker.route)
                        } else {
                            snackbarHostState.showSnackbar("Invalid username or password")
                        }
                    }
                }
            },

            ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(Screen.RegisterScreen.route) }
        ) {
            Text("Create an account",color= Brown500)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val userViewModel = UserViewModel(MockUserDao())
    LoginScreen(navController = navController, viewModel = userViewModel)
}