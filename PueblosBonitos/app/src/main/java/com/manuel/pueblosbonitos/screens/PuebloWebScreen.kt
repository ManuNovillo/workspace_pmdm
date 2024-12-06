package com.manuel.pueblosbonitos.screens

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
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import com.manuel.pueblosbonitos.R
import com.manuel.pueblosbonitos.model.entities.PuebloConProvincia
import com.manuel.pueblosbonitos.viewmodel.MainViewModel

@Composable
fun WebPuebloScreen(viewModel: MainViewModel) {
    val puebloConProvincia by viewModel.puebloDetail
    Scaffold(
        topBar = { WebPuebloTopBar(puebloConProvincia!!) },
        content = { padding -> WebPuebloContent(padding, puebloConProvincia!!) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebPuebloTopBar(puebloConProvincia: PuebloConProvincia) {
    TopAppBar(
        title = { Text(text = puebloConProvincia.pueblo.nombre) },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.purple_700),
            titleContentColor = Color.White
        )
    )
}

@Composable
fun WebPuebloContent(padding: PaddingValues, puebloConProvincia : PuebloConProvincia) {
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
                loadUrl(puebloConProvincia.pueblo.link)
            }
        }, update = {
            it.loadUrl(puebloConProvincia.pueblo.link)
        })
    }
}