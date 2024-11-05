package com.manuel.famososdbjpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.manuel.famososdbjpc.model.FamosoRepository

class FamosoViewModel(application: Application) : AndroidViewModel(application) {
    private var famosoRepository = FamosoRepository(application)
    fun getFamosos() = famosoRepository.getFamosos()
}