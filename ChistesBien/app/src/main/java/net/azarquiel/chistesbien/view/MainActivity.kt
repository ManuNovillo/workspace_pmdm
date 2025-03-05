package net.azarquiel.chistesbien.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.azarquiel.chistesbien.navigation.AppNavigation
import net.azarquiel.chistesbien.ui.theme.ChistesBienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChistesBienTheme {
                AppNavigation(this)
            }
        }
    }
}