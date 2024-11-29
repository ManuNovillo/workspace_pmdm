package com.manuel.agendacontactosjpc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.manuel.agendacontactosjpc.screens.MainScreen
import com.manuel.agendacontactosjpc.ui.theme.AgendaContactosJPCTheme
import com.manuel.agendacontactosjpc.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaContactosJPCTheme {
                MainScreen(MainViewModel(this))
            }
        }
    }
}