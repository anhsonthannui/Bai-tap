package com.example.baitapvenha2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenha2.R
import com.example.baitapvenha2.navigation.Screen

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "Logo UTH",
            modifier = Modifier.height(80.dp)
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Forget Password?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Enter your Email, we will send you a verification code",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = null
            },
            label = { Text("Your Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = emailError != null,
            supportingText = {
                if (emailError != null) {
                    Text(text = emailError!!, color = MaterialTheme.colorScheme.error)
                }
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (email.isNotBlank()) {
                    navController.navigate(Screen.Verification.createRoute(email))
                } else {
                    emailError = "Email cannot be empty."
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
