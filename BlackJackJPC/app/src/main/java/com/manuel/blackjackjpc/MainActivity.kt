package com.manuel.blackjackjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.blackjackjpc.ui.theme.BlackJackJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlackJackJPCTheme {
               MainScreen(MainViewModel(this))
            }
        }
    }
}