package com.manuel.pueblosbonitos.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.pueblosbonitos.ui.theme.PueblosBonitosTheme
import com.manuel.pueblosbonitos.view.navigation.AppNavigation
import com.manuel.pueblosbonitos.view.screens.MainScreen
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PueblosBonitosTheme {
                AppNavigation(this)
            }
        }
    }
}