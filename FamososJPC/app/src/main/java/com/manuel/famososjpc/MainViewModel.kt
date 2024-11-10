package com.manuel.famososjpc

import android.app.Activity.MODE_PRIVATE
import android.os.SystemClock
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.manuel.famososjpc.entities.Person
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    val mainActivity by lazy { mainActivity }

    /**
     * Color inicial de los elementos
     */
    private val colorInicial = Color.Transparent

    /**
     * Número de famosos a mostrar
     */
    val numeroFamosos = 5

    /**
     * Número de aciertos seguidos
     * No se resetea al cambiar de famosos, por lo que son posibles
     * rachas mayores de 5
     */
    val racha = mutableIntStateOf(0)

    /**
     * Lista de todos los famosos
     */
    private lateinit var personajes: ArrayList<Person>

    /**
     * Lista de los famosos que se mostrarán en el juego
     */
    private var personajesGame = ArrayList<Person>()

    /**
     * Lista de los objetos Person que se mostrarán en pantalla,
     * los cuales NO se corresponden a famosos de verdad,
     * sino que son instancias de utilidad para pasar a la MainScreen
     * un nombre y una foto
     */
    val personajesShow = mutableStateListOf<Person>()

    /**
     * Lista de ids de los famosos que se muestran
     */
    private val ids = ArrayList<String>()

    /**
     * Lista de nombres de los famosos que se muestran
     */
    private val nombres = ArrayList<String>()

    /**
     * Lista con los índices de los nombres y fotos que han fallado
     */
    private var errorAnterior = mutableListOf<Int>()

    /**
     * Número de aciertos en la ronda actual
     */
    private var aciertos = 0

    /**
     * Colores de fondo de las fotos
     */
    val coloresFotos = mutableStateListOf<Color>()

    /**
     * Colores de fondo de los nombres
     */
    val coloresNombres = mutableStateListOf<Color>()
    // Inicializadas a -1 para indicar que no hay ninguna foto o nombre pulsado
    // Un valor distinto significa que se ha pulsado una foto o un nombre, respectivamente

    /**
     * Índice de la foto pulsada
     */
    private var pressedFoto = -1

    /**
     * Índice del nombre pulsado
     */
    private var pressedName = -1

    init {
        getPersonajes()
        personajes.shuffle()
        newRonda()
    }

    /**
     * Resetea los colores de las fotos y nombres,
     * y les da el valor inicial
     */
    private fun resetColors() {
        coloresFotos.clear()
        coloresNombres.clear()
        for (i in 0 until numeroFamosos) {
            coloresFotos.add(colorInicial)
            coloresNombres.add(colorInicial)
        }
    }

    /**
     * Crea un nuevo intento de juego
     */
    private fun newRonda() {
        resetColors()
        // Limpiar todas las listas que contengan información sobre el intento
        errorAnterior.clear()
        ids.clear()
        nombres.clear()
        personajesShow.clear()
        personajesGame = ArrayList()

        // Añadir a la lista de personajesGame famosos aleatorios
        for (i in 0 until numeroFamosos) {
            personajesGame.add(personajes[(0..<personajes.size).random()])
        }

        for (person in personajesGame) {
            nombres.add(person.nombre)
            ids.add(person.id)
        }

        // Mezclar solo los nombres para dejar las fotos con el mismo orden
        // que en la lista de personajesGame
        nombres.shuffle()
        for (i in 0 until numeroFamosos) {
            val id = mainActivity.resources.getIdentifier(
                "p${ids[i]}", "drawable", mainActivity.packageName
            )

            /*
             * Crear una nueva instancia de Person cuyo id es el id de la foto
             * Esta instancia no corresponde a un famoso de verdad, es solo
             * para facilitar el trabajo de mostar una foto y un nombre
             * y para la comprobación de aciertos
             */
            personajesShow.add(Person(id.toString(), nombres[i]))
        }
    }

    /**
     * Obtiene los datos de los famosos del shared preferences
     */
    private fun getPersonajes() {
        personajes = ArrayList()
        val personajesAll = mainActivity.getSharedPreferences("person", MODE_PRIVATE).all
        for (entry in personajesAll) {
            val jsonPerson = entry.value.toString()
            val person = Gson().fromJson(jsonPerson, Person::class.java)
            personajes.add(person)
        }
    }


    fun onClickName(i: Int) {
        pressedName = i
        // Si no hay foto pulsada, no comprobar
        if (pressedFoto == -1) return
        check(pressedFoto, pressedName)
    }

    fun onClickFoto(i: Int) {
        pressedFoto = i
        // Si no hay nombre pulsado, no comprobar
        if (pressedName == -1) return
        check(pressedFoto, pressedName)
    }

    /**
     * Comprueba si el nombre y la foto pulsados coinciden
     * Si coinciden, a ambos se les pone el fondo verde.
     * Si no, se pone rojo
     * Si todos los nombres y fotos han sido ya unidos correctamente,
     * se crea un nueva ronda
     */
    private fun check(fotoPressed: Int, namePressed: Int) {
        if (errorAnterior.isNotEmpty()) {
            coloresFotos[errorAnterior[0]] = Color.Transparent
            coloresNombres[errorAnterior[1]] = Color.Transparent
        }
        if (personajesGame[fotoPressed].nombre == nombres[namePressed]) {
            coloresFotos[fotoPressed] = Color.Green
            coloresNombres[namePressed] = Color.Green
            resetErrorAnterior()
            racha.intValue++
            aciertos++
            if (aciertos == 5) {
                // Esperar un poco antes de mostrar los nuevos famosos
                GlobalScope.launch {
                    SystemClock.sleep(500)
                    launch(Main) {
                        newRonda()
                    }
                }
                aciertos = 0
            }
        } else {
            racha.intValue = 0
            coloresFotos[fotoPressed] = Color.Red
            coloresNombres[namePressed] = Color.Red
            resetErrorAnterior()
            errorAnterior.add(fotoPressed)
            errorAnterior.add(namePressed)
        }
        pressedFoto = -1
        pressedName = -1
    }

    private fun resetErrorAnterior() {
        errorAnterior.clear()
    }
}
