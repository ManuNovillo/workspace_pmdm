package net.azarquiel.metroroomjpc

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.alltricks.util.Util
import net.azarquiel.metroroomjpc.model.LineaWithEstaciones
import net.azarquiel.metroroomjpc.viewmodel.LineaViewModel

class MainViewModel(mainActivity: MainActivity): ViewModel()  {
    val lineas = mutableStateListOf<LineaWithEstaciones>()
    val linea = mutableStateOf<LineaWithEstaciones?>(null)

    init {
        val lineaViewModel = ViewModelProvider(mainActivity)[LineaViewModel::class.java]
        Util.inyecta(mainActivity, "MetroDB.db")
        lineaViewModel.getAllLineas().observe(mainActivity) { lineasData ->
            lineas.addAll(lineasData)
        }
    }

    fun setLinea(lineaWithEstaciones: LineaWithEstaciones) {
        linea.value = lineaWithEstaciones
    }
}
