package com.manuel.blackjackjpc

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.blackjackjpc.model.Carta
import com.manuel.blackjackjpc.model.Palo
import kotlin.random.Random

class MainViewModel(activity: MainActivity) : ViewModel() {
    private var mainActivity = activity
    val mazo = Array(40) { Carta() }
    val random = Random(System.currentTimeMillis())
    private val _cartasJugadas = mutableStateListOf<Carta>()
    val cartasJugadas: SnapshotStateList<Carta> = _cartasJugadas
    var posMazo = 0
    private val _puntosJugadores = MutableLiveData<List<Int>>(emptyList())
    val puntosJugadores: LiveData<List<Int>> = _puntosJugadores
    private var jugador = 0
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        loadMazo()
        sacaCarta()
        sacaCarta()
    }

    private fun loadMazo() {
        var i = 0
        for (palo in 0 until 4) {
            for (numero in 1..10) {
                mazo[i] = Carta(Palo.entries[palo], numero)
                i++
            }
        }
        mazo.shuffle(random)
    }

    fun sacaCarta() {
        Log.d("manu", "prueba")
        val carta = mazo[posMazo]
        posMazo++
        _cartasJugadas.add(0, carta)
        sumaPuntos(carta.numero)
        Log.d("manu", "${_puntosJugadores.value!![0]}")
    }

    fun sumaPuntos(num: Int) {
        var aux = ArrayList(_puntosJugadores.value!!)
        aux[jugador] += if (num > 7) 10 else num
        //Log.d("manu", "Sumado ${_puntosJugadores.value!![jugador]}")
        _puntosJugadores.value = ArrayList(aux)
        if (_puntosJugadores.value!![jugador] > 21) nextPlayer()

    }

    private fun nextPlayer() {

        if (jugador == 0) {
            jugador = 1
            _cartasJugadas.clear()
            sacaCarta()
            sacaCarta()
        } else {
            checkWinner()
        }
    }

    private fun checkWinner() {
        if (_puntosJugadores.value!![0] > 21 && _puntosJugadores.value!![1] > 21) {
            _msg.value = "Tablas"
        } else if (_puntosJugadores.value!![0] > 21 && _puntosJugadores.value!![1] <= 21) {
            _msg.value =
                "Gana el jugador 2 \n\n P1 (${_puntosJugadores.value!![0]})- P2 (${_puntosJugadores.value!![1]})"
        } else if (_puntosJugadores.value!![0] <= 21 && _puntosJugadores.value!![1] > 21) {
            _msg.value =
                "Gana el jugador 1 \n\n P1 (${_puntosJugadores.value!![0]})- P2 (${_puntosJugadores.value!![1]})"
        } else if (_puntosJugadores.value!![0] > _puntosJugadores.value!![1]) {
            _msg.value =
                "Gana el jugador 1 \n\n P1 (${_puntosJugadores.value!![0]})- P2 (${_puntosJugadores.value!![1]})"
        } else if (_puntosJugadores.value!![0] < _puntosJugadores.value!![1]) {
            _msg.value =
                "Gana el jugador 2 \n\n P1 (${_puntosJugadores.value!![0]})- P2 (${_puntosJugadores.value!![1]})"
        } else {
            _msg.value = "Tablas"
        }
        // _openDialog.value = true
        Toast.makeText(mainActivity.baseContext, msg.value, Toast.LENGTH_SHORT).show()
    }

    fun stop() {
        nextPlayer()
    }
}

