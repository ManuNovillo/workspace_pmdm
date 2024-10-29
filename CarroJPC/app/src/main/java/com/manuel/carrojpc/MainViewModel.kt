package com.manuel.carrojpc

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.manuel.carrojpc.model.Producto

class MainViewModel(mainActivity: MainActivity) {
    private var carroSH: SharedPreferences =
        mainActivity.getSharedPreferences("carro", Context.MODE_PRIVATE)
    var productos = mutableStateListOf<Producto>()
    val mainActivity by lazy { mainActivity }
    private var _openDialog = MutableLiveData(false)
    val openDialog: LiveData<Boolean> = _openDialog

    init {
        getProductos()
    }

    fun setDialog(value: Boolean) {
        _openDialog.value = value
    }

    fun addProducto(producto: Producto) {
        producto.id = carroSH.all.size + 1
        productos.add(0, producto)
        val json = Gson().toJson(producto)
        val editor = carroSH.edit()
        editor.putString(producto.id.toString(), json)
        editor.apply()
    }

    private fun getProductos() {
        val carroAll = carroSH.all
        productos.clear()
        for ((key, value) in carroAll) {
            val jsonProducto = value.toString()
            val producto = Gson().fromJson(jsonProducto, Producto::class.java)
            productos.add(0, producto)
        }
    }

    fun getProducto(id: Int): Producto {
        val jsonProducto = carroSH.getString(id.toString(), null)
        return Gson().fromJson(jsonProducto, Producto::class.java)
    }
}