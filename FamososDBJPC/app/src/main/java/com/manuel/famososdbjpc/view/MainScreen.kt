package com.manuel.famososdbjpc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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
    val famosos by viewModel.famosos.observeAsState(emptyList())
    Column(modifier = Modifier.padding(padding)) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchView(textState)
        LazyColumn {
            itemsIndexed(famosos.filter { famoso ->
                famoso.nombre.contains(textState.value.text, ignoreCase = true)
            }) {i, famoso ->
                if (i == 0) {
                    Image(
                        painter = painterResource(id = R.drawable.header),
                        contentDescription = "Fondo",
                        modifier = Modifier.fillMaxWidth(),

                    )
                }
                CustomCard(famoso, viewModel)
            }
        }
    }

}

@Composable
fun CustomCard(famoso: Famoso, viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),

        ) {
        Row(
            modifier = Modifier
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(
                    id = viewModel.mainActivity.resources.getIdentifier(
                        "p${famoso.id}",
                        "drawable",
                        viewModel.mainActivity.packageName)),
                contentDescription = "Imagen de ${famoso.nombre}",
                modifier = Modifier.weight(3f).size(100.dp,150.dp).padding(8.dp)


            )

            Text(
                text = famoso.nombre,
                fontSize = 20.sp,
                modifier = Modifier.weight(8f).fillMaxWidth(),
                textAlign = TextAlign.Center
                )
        }
    }
}

@Composable
fun SearchView(
    state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = {value->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(text = "Search here...")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedPlaceholderColor = Color.Red,
            unfocusedPlaceholderColor = Color.Yellow,

        ),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}
