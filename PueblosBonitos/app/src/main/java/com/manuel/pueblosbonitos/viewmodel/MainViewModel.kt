package com.manuel.pueblosbonitos.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuel.pueblosbonitos.model.entities.Comunidad
import com.manuel.pueblosbonitos.model.entities.Pueblo
import com.manuel.pueblosbonitos.model.entities.PuebloConProvincia
import com.manuel.pueblosbonitos.util.Util
import com.manuel.pueblosbonitos.view.MainActivity

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val showFilter = mutableStateOf(false)
    val mainActivity by lazy { mainActivity }
    val comunidades = mutableStateListOf<Comunidad>()
    val pueblosConProvincia = mutableStateListOf<PuebloConProvincia>()
    val mostrarSoloFavoritos = mutableStateOf(false)
    val puebloDetail = mutableStateOf<PuebloConProvincia?>(null)
    val esFavorito = mutableStateOf(false)
    val nombreComunidad = mutableStateOf("")

    private val puebloViewModel: PuebloViewModel = ViewModelProvider(mainActivity)[PuebloViewModel::class]

    init {
        Util.inyecta(mainActivity, "pueblosbonitos.sqlite")
        val comunidadViewModel = ViewModelProvider(mainActivity)[ComunidadViewModel::class.java]
        comunidadViewModel.getAllComunidades().observe(mainActivity) { comunidadesDatos ->
            comunidades.addAll(comunidadesDatos)
        }

    }

    fun prepararPueblosScreen(comunidad: Comunidad) {
        mostrarSoloFavoritos.value = false
        nombreComunidad.value = comunidad.nombre
        puebloViewModel.getAllPueblosByComunidad(comunidad.id).observe(mainActivity) { pueblosDatos ->
            pueblosConProvincia.clear()
            pueblosConProvincia.addAll(pueblosDatos)
        }
    }

    fun prepararPuebloDetailScreen(puebloConProvincia: PuebloConProvincia) {
        esFavorito.value = puebloConProvincia.pueblo.fav == 1
        showFilter.value = false
        this.puebloDetail.value = puebloConProvincia
    }

    fun toggleFavoritos() {
        mostrarSoloFavoritos.value = !mostrarSoloFavoritos.value
    }

    fun updatePueblo(pueblo: Pueblo) {
        pueblo.fav = if (pueblo.fav == 1) 0 else 1
        esFavorito.value = pueblo.fav == 1
        puebloViewModel.updatePueblo(pueblo)
    }

    fun toggleFilter() {
        showFilter.value = !showFilter.value
    }
}