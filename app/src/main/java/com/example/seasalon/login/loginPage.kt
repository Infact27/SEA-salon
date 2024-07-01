@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.seasalon.login
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.seasalon.Homepage
import com.example.seasalon.ui.theme.SEASalonTheme

@Composable
fun LoginPage(navController: NavController, viewModel: AuthViewModel, onLoginSuccess: (User) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            viewModel.loginUser(email, password) { success, user ->
                if (success && user != null) {
                    onLoginSuccess(user)
                } else {
                    errorMessage = "Login failed"
                }
            }
        }) {
            Text("Login")
        }
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }
    }
}
