package com.manuel.famososdbjpc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuel.famososdbjpc.R
import com.manuel.famososdbjpc.model.Famoso
import com.manuel.famososdbjpc.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "Famosos") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    val famosos = remember { mutableStateListOf<Famoso>(Famoso(1, "Juan", 1), Famoso(1, "Juan", 1), Famoso(1, "Juan", 1)) }
    LazyColumn {
        items(famosos) {famoso ->
            CustomCard(famoso)
        }
    }
}

@Composable
fun CustomCard(famoso: Famoso) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Imagen de ${famoso.nombre}",
                modifier = Modifier
                    .weight(1f)

            )

            Text(
                text = famoso.nombre ?: "",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    CustomCard(Famoso(1, "Juan", 1))
}
