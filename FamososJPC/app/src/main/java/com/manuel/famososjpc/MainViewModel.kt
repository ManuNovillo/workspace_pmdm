package com.manuel.famososjpc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.manuel.famososjpc.entities.Person

class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    val mainActivity = mainActivity
    private lateinit var personajes: ArrayList<Person>
    var personajesGame = ArrayList<Person>()
    private var _nombres = MutableLiveData<ArrayList<String>>()
    val nombres: LiveData<ArrayList<String>> = _nombres
    private var _fotos = MutableLiveData<ArrayList<Int>>()
    val fotos: LiveData<ArrayList<Int>> = _fotos

    init {
        getPersonajes()
        startGame()
    }

    private fun startGame() {
        personajes.shuffle()
        personajesGame = ArrayList(personajes.take(5))
        _nombres.value = ArrayList()
        _fotos.value = ArrayList()
        for (person in personajesGame) {
            _nombres.value?.add(person.name)
            val id = mainActivity.resources.getIdentifier(person.id, "drawable", mainActivity.packageName)
            _fotos.value?.add(id)
        }
        _nombres.value!!.shuffle()
        _fotos.value!!.shuffle()
    }

    private fun getPersonajes() {
        personajes = ArrayList()
        val personajesAll = mainActivity.getPreferences(Context.MODE_PRIVATE).all
        for ((key, value) in personajesAll) {
            val jsonPerson = value.toString()
            val person = Gson().fromJson(jsonPerson, Person::class.java)
            personajes.add(person)
        }
    }

    fun clickItem(i: Int) {

    }
}
