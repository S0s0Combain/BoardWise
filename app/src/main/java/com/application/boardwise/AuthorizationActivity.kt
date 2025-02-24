package com.application.boardwise

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.boardwise.ui.theme.BoardWiseTheme
import com.application.boardwise.ui.theme.DarkBlue

class AuthorizationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoardWiseTheme {
                val window: Window = this.window
                window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
                    AuthApp(innerPaddings)
                }
            }
        }
    }
}

@Composable
fun AuthApp(innerPaddings: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", modifier = Modifier.fillMaxSize().padding(innerPaddings)) {
        composable("login") { LoginScreen(navController = navController) }
        composable("register") { RegisterScreen(navController = navController) }
    }
}