package com.manuel.pueblosbonitos.model.repositories

import android.app.Application
import com.manuel.pueblosbonitos.model.db.PueblosBonitosDB

class ComunidadRepository(application: Application) {

    val daoComunidad = PueblosBonitosDB.getDatabase(application).daoComunidad()

    fun getAllComunidades() = daoComunidad.getAllComunidades()
}