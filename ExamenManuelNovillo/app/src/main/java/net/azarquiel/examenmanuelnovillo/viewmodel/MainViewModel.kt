package net.azarquiel.examenmanuelnovillo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.azarquiel.examenmanuelnovillo.model.CostaConPlayas
import net.azarquiel.examenmanuelnovillo.util.Util
import net.azarquiel.examenmanuelnovillo.view.MainActivity
import kotlin.math.cos

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    private var _costas = MutableLiveData<List<CostaConPlayas>>()
    val costas: LiveData<List<CostaConPlayas>> = _costas

    private var _costaSeleccionada = MutableLiveData<CostaConPlayas>()
    val costaSeleccionada: LiveData<CostaConPlayas> = _costaSeleccionada

    private var _soloAzules = MutableLiveData(false)
    val soloAzules: LiveData<Boolean> = _soloAzules

    val mainActivity = mainActivity

    init {
        Util.inyecta(mainActivity, "playasandalu.db")
        val costaViewModel = ViewModelProvider(mainActivity)[CostaViewModel::class]
        costaViewModel.getAllCostas().observe(mainActivity) { costasDatos ->
            _costas.value = costasDatos
            costasDatos.forEach { costa ->
                Log.d("MANU", costa.playas.toString())
            }
        }
    }

    fun prepararDetailScreen(costaConPlayas: CostaConPlayas) {
        _costaSeleccionada.value = costaConPlayas
    }

    fun alternarMostarSoloAzules() {
        _soloAzules.value = !_soloAzules.value!!
    }

    fun prepararPlayasScreen() {
        _soloAzules.value = false
    }
}