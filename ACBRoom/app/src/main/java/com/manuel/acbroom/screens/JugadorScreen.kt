package com.manuel.acbroom.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.manuel.acbroom.viewmodel.MainViewModel


@Composable
fun JugadorScreen(viewModel: MainViewModel) {
    Scaffold (
        topBar = { JugadorTopBar() },
        content = { padding -> JugadorContent(padding, viewModel) }

    )
}

@Composable
fun JugadorTopBar() {
    TODO("Not yet implemented")
}

@Composable
fun JugadorContent(padding: Any, viewModel: MainViewModel) {

}