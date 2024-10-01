package com.manuel.likesViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MyViewModel : ViewModel() {
    private val _num = MutableLiveData(50)
    val num: LiveData<Int> = _num

    private val _intentos= MutableLiveData(0)
    val intentos: LiveData<Int> = _intentos

    private val _random = MutableLiveData( (0..100).random(Random(System.currentTimeMillis())))
    val random: LiveData<Int> = _random

    fun incrementIntentos() {
        _intentos.value = _intentos.value?.plus(1)
    }
    fun incrementNum(num: Int) {
        _num.value = _num.value?.plus(num)
    }

    fun checkRandom(num: Int) : String {
        if (num == random.value) {
            return "SI"
        }
        if (num > random.value!!) {
            return "Menor"
        }
        return "Mayor"
    }
}