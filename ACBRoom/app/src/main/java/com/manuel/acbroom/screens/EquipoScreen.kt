package com.manuel.acbroom.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.manuel.acbroom.model.Equipo
import com.manuel.acbroom.viewmodel.MainViewModel

@Composable
fun EquipoScreen(viewModel: MainViewModel) {
    val equipo by viewModel.equipo
    Scaffold(
        topBar = { EquipoTopBar(equipo!!.nombre) },
        content = { padding -> EquipoContent(padding, equipo!!) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoTopBar(nombreEquipo: String) {
    TopAppBar(
        title = { Text(text = nombreEquipo) }
    )
}

@Composable
fun EquipoContent(padding: PaddingValues, equipo: Equipo) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
//        AndroidView(factory = {
//            WebView(it).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                webViewClient = WebViewClient()
//                loadUrl(equipo)
//            }
//        }, update = {
//            it.loadUrl(jugadorConEquipo.jugador.link)
//        })
    }
}
