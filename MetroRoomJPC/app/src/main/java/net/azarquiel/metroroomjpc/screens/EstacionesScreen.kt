import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import net.azarquiel.metroroomjpc.MainViewModel
import net.azarquiel.metroroomjpc.model.Estacion


@Composable
fun EstacionesScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBarEstaciones() },
        content = { padding ->
            CustomContentEstaciones(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBarEstaciones() {
    TopAppBar(
        title = { Text(text = "Title") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}


@Composable
fun CustomContentEstaciones(padding: PaddingValues, viewModel: MainViewModel) {
    val linea = viewModel.linea
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    )
    {
        // a pintaaarrrr sabiendo que estamos en column
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = linea.value?.linea?.nombre ?: ""
        )

        LazyColumn {
            items(linea.value!!.estaciones) { estacion ->
                CardEstacion(estacion, linea.value!!.linea.color)
            }
        }
    }
}

@Composable
fun CardEstacion(estacion: Estacion, color: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(color.toColorInt()),
            contentColor = Color.Black),
    ) {
        Text(
            text = estacion.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
