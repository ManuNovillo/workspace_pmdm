package com.manuel.acbroom.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.acbroom.ui.theme.ACBRoomTheme
import com.manuel.acbroom.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACBRoomTheme {
                MainScreen(MainViewModel(this))
            }
        }
    }
}