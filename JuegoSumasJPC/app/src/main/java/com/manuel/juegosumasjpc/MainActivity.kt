package com.manuel.juegosumasjpc

import MainScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.juegosumasjpc.ui.theme.JuegoSumasJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuegoSumasJPCTheme {
                MainScreen(MainViewModel(this))
            }
        }
    }
}