package com.manuel.parquesnaturalesjpc.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.manuel.parquesnaturalesjpc.navigation.AppNavigation
import com.manuel.parquesnaturalesjpc.ui.theme.ParquesNaturalesJPCTheme
import com.manuel.parquesnaturalesjpc.viewmodel.MainViewModel
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val activity = this
        val viewModel: MainViewModel = ViewModelProvider.create(
            this,
            factory = MainViewModel.Factory,
            extras = MutableCreationExtras().apply {
                set(MainViewModel.MY_REPOSITORY_KEY, activity)
            },
        )[MainViewModel::class]
        setContent {
            ParquesNaturalesJPCTheme {
                AppNavigation(viewModel)
            }
        }
    }
}
