package com.example.baitapvenha2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.baitapvenha2.navigation.AppNavigation
import com.example.baitapvenha2.ui.theme. BaiTapVeNha2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Sử dụng Theme của ứng dụng của bạn
            BaiTapVeNha2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi hàm điều hướng chính của ứng dụng
                    AppNavigation()
                }
            }
        }
    }
}
