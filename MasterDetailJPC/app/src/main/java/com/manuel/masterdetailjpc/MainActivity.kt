package com.manuel.masterdetailjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manuel.masterdetailjpc.navigation.AppNavigation
import com.manuel.masterdetailjpc.ui.theme.MasterDetailJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MasterDetailJPCTheme {
                AppNavigation()
            }
        }
    }
}