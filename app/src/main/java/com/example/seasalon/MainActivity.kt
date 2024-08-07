package com.example.seasalon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seasalon.login.AuthViewModel
import com.example.seasalon.login.LoginPage
import com.example.seasalon.login.User
import com.example.seasalon.ui.theme.SEASalonTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            SEASalonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "homepage"
                    ){
                        composable("loginpage") { LoginPage(navController, AuthViewModel() , onLoginSuccess = {User -> User} )}
                        composable("homepage") { Homepage(navController) }
                        composable("reviewscreen") { ReviewScreen(navController) }
                        composable("reservasi") {Reservasi(navController)}
                    }
                }
            }
        }
    }
}