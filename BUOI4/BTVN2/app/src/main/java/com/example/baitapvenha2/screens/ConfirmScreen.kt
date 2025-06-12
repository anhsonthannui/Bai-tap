package com.example.baitapvenha2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baitapvenha2.R
import com.example.baitapvenha2.navigation.Screen

@Composable
fun ConfirmScreen(navController: NavController, email: String, password: String) {
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
                text = "Confirm",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Please confirm your details.",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "123456",
                onValueChange = {},
                label = { Text("Verification Code") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {},
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp)
            )
        }

        Button(
            onClick = {
                navController.navigate(Screen.ForgetPassword.route) {
                    popUpTo(Screen.ForgetPassword.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
        ) {
            Text(text = "Summit", fontSize = 18.sp)
        }
    }
}
