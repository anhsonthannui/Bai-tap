package com.example.baitapvenha2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitapvenha2.R
import com.example.baitapvenha2.navigation.Screen

@Composable
fun VerificationScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val correctCode = "123456"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "Logo UTH",
                modifier = Modifier.height(80.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Verify Code",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Enter the code sent to $email",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = code,
                onValueChange = {
                    if (it.length <= 6) {
                        code = it
                        isError = false
                    }
                },
                label = { Text("Verification Code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = "Invalid code, please try again.",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                shape = RoundedCornerShape(12.dp)
            )
        }

        Button(
            onClick = {
                if (code == correctCode) {
                    isError = false
                    navController.navigate(Screen.CreatePassword.createRoute(email))
                } else {
                    isError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
        ) {
            Text(text = "Next", fontSize = 18.sp)
        }
    }
}
