package com.manuel.juegosumasjpc

import android.os.SystemClock
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(mainActivity: MainActivity) : ViewModel() {

    val mainActivity by lazy { mainActivity }
    private var _primerNum = MutableLiveData("")
    val primerNum: LiveData<String> = _primerNum
    private var _segundoNum = MutableLiveData("")
    val segundoNum: LiveData<String> = _segundoNum
    private var _randomNum = MutableLiveData(0)
    val randomNum: LiveData<Int> = _randomNum
    private var _aciertos = MutableLiveData(0)
    val aciertos: LiveData<Int> = _aciertos
    private var _intentos = MutableLiveData(0)
    val intentos: LiveData<Int> = _intentos
    private var _backgroundColor = MutableLiveData(Color.White)
    val backgroundColor: LiveData<Color> = _backgroundColor
    private var primero = true
    private var _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog
    private var _time = MutableLiveData("")
    val time: LiveData<String> = _time
    private var inicio: Long
    private var wait: Boolean

    init {
        startGame()
        inicio = System.currentTimeMillis()
        wait = false
    }

    private fun startGame() {
        newNumber()
    }

    private fun newNumber() {
        _randomNum.value = (2..18).random()
        _backgroundColor.value = Color.White
        _primerNum.value = ""
        _segundoNum.value = ""
    }


    fun onButtonClick(n: Int) {
        if (wait) return
        if (primero) {
            _primerNum.value = n.toString()
            primero = false
        } else {
            _segundoNum.value = n.toString()
            checkResultado()
            primero = true
        }
    }

    private fun checkResultado() {
        if (primerNum.value?.toInt()!! + segundoNum.value?.toInt()!! == randomNum.value) {
            _backgroundColor.value = Color.Green
            _aciertos.value = aciertos.value?.plus(1)
            _intentos.value = intentos.value?.plus(1)
        } else {
            _backgroundColor.value = Color.Red
            _intentos.value = intentos.value?.plus(1)
        }
        if (intentos.value == 5) gameOver()
        else {
            GlobalScope.launch {
                wait = true
                SystemClock.sleep(1000)
                launch(Main) {
                    newNumber()
                    wait = false
                }
            }
        }
    }

    private fun gameOver() {
        _openDialog.value = true
        _time.value = ((System.currentTimeMillis() - inicio) / 1000).toString()
    }

    private fun hideDialog() {
        _openDialog.value = false
    }

    fun restartGame() {
        hideDialog()
        _aciertos.value = 0
        _intentos.value = 0
        startGame()
        inicio = System.currentTimeMillis()
    }
}