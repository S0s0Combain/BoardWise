package com.application.boardwise

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    var login by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var loginError by remember {
        mutableStateOf<String?>(null)
    }

    var emailError by remember {
        mutableStateOf<String?>(null)
    }

    var passwordError by remember {
        mutableStateOf<String?>(null)
    }

    var confirmPasswordError by remember {
        mutableStateOf<String?>(null)
    }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.logo),
            contentDescription = "The app's logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(text = "Login") },
            modifier = Modifier.fillMaxWidth(),
            isError = loginError != null,
            supportingText = {
                if (loginError != null) {
                    Text(text = loginError!!, color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError != null,
            supportingText = {
                if (emailError != null) {
                    Text(text = emailError!!, color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) {
                    Text(text = passwordError!!, color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError != null,
            supportingText = {
                if (confirmPasswordError != null) {
                    Text(text = confirmPasswordError!!, color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                loginError = null
                emailError = null
                passwordError = null
                confirmPasswordError = null

                if (login.isEmpty()) {
                    loginError = "Login cannot be empty"
                    return@Button
                }

                if (email.isEmpty()) {
                    emailError = "Email cannot be empty"
                    return@Button
                }

                if (password.isEmpty()) {
                    passwordError = "Password cannot be empty"
                    return@Button
                }

                if (confirmPassword.isEmpty()) {
                    confirmPasswordError = "Confirm Password cannot be empty"
                    return@Button
                }

                if (!isValidLogin(login)) {
                    loginError = "Login must contain only letters, numbers, and underscores"
                    return@Button
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Invalid email format"
                    return@Button
                }

                if (!isValidPassword(password)) {
                    passwordError =
                        "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character"
                    return@Button
                }

                if (password != confirmPassword) {
                    confirmPasswordError = "Passwords do not match"
                    return@Button
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Register")
        }
        TextButton(onClick = { navController.navigate("login") }) {
            Text(text = "Already have an account? Login")
        }
    }
}

fun isValidLogin(login: String): Boolean {
    val loginPattern = "^[a-zA-Z0-9_]+$".toRegex()
    return loginPattern.matches(login)
}

fun isValidPassword(password: String): Boolean {
    val passwordPattern =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&.])[A-Za-z\\d@\$!%*?&.]{8,}\$".toRegex()
    return passwordPattern.matches(password)
}