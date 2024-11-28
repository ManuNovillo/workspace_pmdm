package com.manuel.acbroom.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.manuel.acbroom.model.Jugador
import com.manuel.acbroom.model.JugadorConEquipo
import com.manuel.acbroom.viewmodel.MainViewModel

@Composable
fun WebJugadorScreen(viewModel: MainViewModel) {
    val jugadorConEquipo by viewModel.jugadorConEquipo
    Scaffold(
        topBar = { WebJugadorTopBar(jugadorConEquipo!!) },
        content = { padding -> WebJugadorContent(padding, jugadorConEquipo!!) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebJugadorTopBar(jugadorConEquipo: JugadorConEquipo) {
    TopAppBar(
        title = { Text(text = jugadorConEquipo.jugador.nombre) }
    )
}

@Composable
fun WebJugadorContent(padding: PaddingValues, jugadorConEquipo : JugadorConEquipo) {
    Column(
        modifier = Modifier
            .padding(padding)
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(jugadorConEquipo.jugador.link)
            }
        }, update = {
            it.loadUrl(jugadorConEquipo.jugador.link)
        })
    }
}