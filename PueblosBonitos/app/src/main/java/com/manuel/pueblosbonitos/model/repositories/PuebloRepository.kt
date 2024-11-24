package com.manuel.pueblosbonitos.model.repositories

import android.app.Application
import com.manuel.pueblosbonitos.model.db.PueblosBonitosDB
import com.manuel.pueblosbonitos.model.entities.Pueblo

class PuebloRepository(application: Application) {

    val daoPueblo = PueblosBonitosDB.getDatabase(application).daoPueblo()

    fun getAllPueblosByComunidad(comunidadId: Int) = daoPueblo.getAllPueblosByComunidad(comunidadId)

    fun updatePueblo(pueblo: Pueblo) = daoPueblo.updatePueblo(pueblo)
}