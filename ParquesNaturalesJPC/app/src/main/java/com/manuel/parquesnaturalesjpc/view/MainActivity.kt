package com.manuel.parquesnaturalesjpc.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.parquesnaturalesjpc.navigation.AppNavigation
import com.manuel.parquesnaturalesjpc.ui.theme.ParquesNaturalesJPCTheme
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = MainViewModel(this)
        setContent {
            ParquesNaturalesJPCTheme {
                AppNavigation(viewModel)
            }
        }
    }
}

