package com.manuel.juegotraficojpc

import android.app.Activity.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.juegotraficojpc.model.Sign
import com.manuel.juegotraficojpc.util.Util

class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    val mainActivity by lazy { mainActivity }
    var azar: Int = 0
    val signsArray = Array(400) { Sign() }
    val jugadaArray = Array(4) { Sign() }
    private var _jugadaFotos: MutableLiveData<IntArray> = MutableLiveData(IntArray(4) {0})
    val jugadaFotos: LiveData<IntArray> = _jugadaFotos
    private var _signBuscar = MutableLiveData<Sign>()
    val signBuscar: LiveData<Sign> = _signBuscar
    init {
        Util.inyecta(mainActivity, "signs.xml")
        getAllSigns()
        newIntento()
    }

    fun onClickSign(num: Int) {
        Log.d("kk2", num.toString())
        if (num == azar) Toast.makeText(mainActivity.baseContext, "Correcto", Toast.LENGTH_SHORT).show()
        else Toast.makeText(mainActivity.baseContext, "Incorrecto", Toast.LENGTH_SHORT).show()

        newIntento()
    }

    private fun getAllSigns() {
        val sh = mainActivity.getSharedPreferences("signs", MODE_PRIVATE)
        val signsSH = sh.all
        var i = 0
        signsSH.forEach { (key, value) ->
            var text = value.toString()
            while (text.contains("  ")) {
                text = text.replace("  ", " ")
            }
            signsArray[i] = Sign(key, text)
            i++
        }
    }

    private fun newIntento() {
        signsArray.shuffle()
        for (i in 0..3) {
            jugadaArray[i] = signsArray[i]
            //Log.d("pruebaa",  "${if (jugadaArray[i].id.length == 1) "0${jugadaArray[i].id}" else jugadaArray[i].id}")
            val id = if (jugadaArray[i].id.length == 1) "0${jugadaArray[i].id}" else jugadaArray[i].id

            _jugadaFotos.value!![i] = mainActivity.resources.getIdentifier(
                "s$id",
                "drawable",
                mainActivity.packageName
            )
        }
        azar = (0 until 4).random()
        _signBuscar.value = jugadaArray[azar]

    }
}