package com.manuel.famososjpc

import android.app.Activity.MODE_PRIVATE
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.manuel.famososjpc.entities.Person

class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    val mainActivity by lazy { mainActivity }
    val numeroFamosos = 5
    private lateinit var personajes: ArrayList<Person>
    var personajesGame = ArrayList<Person>()
    private var _personajesShow = MutableLiveData(Array(5) { Person() })
    val personajesShow: LiveData<Array<Person>> = _personajesShow
    private val ids = ArrayList<String>()
    private val nombres = ArrayList<String>()
    val colores =
        mutableStateListOf(Color.White, Color.White, Color.White, Color.White, Color.White)
    private var pressedFoto = -1
    private var pressedName = -1

    init {
        getPersonajes()
        startGame()
    }

    private fun startGame() {
        personajes.shuffle()
        personajesGame = ArrayList(personajes.take(5))
        _personajesShow.value = Array(5) { Person() }
        for (person in personajesGame) {
            nombres.add(person.nombre)
            ids.add(person.id)
        }
        nombres.shuffle()
        ids.shuffle()
        for (i in 0 until numeroFamosos) {
            _personajesShow.value!![i].nombre = nombres[i]
            _personajesShow.value!![i].id = ids[i]
        }
    }

    private fun getPersonajes() {
        personajes = ArrayList()
        val personajesAll = mainActivity.getSharedPreferences("person", MODE_PRIVATE).all
        for ((key, value) in personajesAll) {
            val jsonPerson = value.toString()
            val person = Gson().fromJson(jsonPerson, Person::class.java)
            personajes.add(person)
        }
    }

    fun clickName(i: Int) {
        Log.d("COJONES", "clickName: $i")
        if (pressedName != -1) return
        pressedName = i
        if (pressedFoto == -1) return
        check(pressedFoto, pressedName)
    }

    private fun check(fotoPressed: Int, namePressed: Int) {
        var correcto = false
        val clickedPerson = Person(ids[fotoPressed], nombres[namePressed])

        for (person in personajesGame) {
            Log.d("COJONES", "check: $person")
            Log.d("COJONES", "check: $clickedPerson")
            if (person == clickedPerson) {
                correcto = true
            }
        }
        if (correcto) colores[namePressed] = Color.Green
        else colores[namePressed] = Color.Red
        pressedFoto = -1
        pressedName = -1
    }

    fun clickFoto(i: Int) {
        Log.d("COJONES", "clickFoto $i")
        if (pressedFoto != -1) return
        pressedFoto = i
        if (pressedName == -1) return
        check(pressedFoto, pressedName)
    }
}
