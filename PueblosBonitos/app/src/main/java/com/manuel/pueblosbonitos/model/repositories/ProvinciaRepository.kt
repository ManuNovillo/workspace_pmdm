package com.manuel.pueblosbonitos.model.repositories

import android.app.Application
import com.manuel.pueblosbonitos.model.db.PueblosBonitosDB

class ProvinciaRepository(application: Application) {

    val daoProvincia = PueblosBonitosDB.getDatabase(application).daoProvincia()

    fun getAllProvincias(comunidadId: Int) = daoProvincia.getAllProvincias(comunidadId)

}