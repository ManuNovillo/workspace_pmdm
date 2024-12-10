package net.azarquiel.examenmanuelnovillo.model

import android.app.Application

class CostaRepository(application: Application) {

    val daoCosta = PlayasDB.getDatabase(application).daoCosta()

    fun getAllCostas() = daoCosta.getAllCostas()

}