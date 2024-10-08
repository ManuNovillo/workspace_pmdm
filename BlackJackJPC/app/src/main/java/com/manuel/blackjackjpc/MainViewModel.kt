package com.manuel.blackjackjpc

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.blackjackjpc.model.Carta
import com.manuel.blackjackjpc.model.Palo
import kotlin.random.Random

class MainViewModel : ViewModel() {
    val mazo = Array(40) { Carta() }
    val random = Random(System.currentTimeMillis())
    private val _cartasJugadas = mutableStateListOf<Carta>()
    val cartasJugadas: SnapshotStateList<Carta> = _cartasJugadas
    var posMazo = 0
    private var puntos = 0

    init {
        loadMazo()
        sacaCarta()
        sacaCarta()
    }

    fun loadMazo() {
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
        val carta = mazo[posMazo]
        posMazo++
        _cartasJugadas.add(0, carta)
        sumaPuntos(carta.numero)
    }

    fun sumaPuntos(num: Int) {
        puntos += num
        check21()
    }

    private fun check21() {
        if (puntos > 21) {

        }
    }
}
