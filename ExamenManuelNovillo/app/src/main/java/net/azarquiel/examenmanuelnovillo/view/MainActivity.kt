package net.azarquiel.examenmanuelnovillo.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.examenmanuelnovillo.navigation.AppNavigation
import net.azarquiel.examenmanuelnovillo.ui.theme.ExamenManuelNovilloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenManuelNovilloTheme {
                AppNavigation(this)
            }
        }
    }
}