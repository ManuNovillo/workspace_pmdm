package net.azarquiel.metroroomjpc.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.metroroomjpc.model.Linea
import net.azarquiel.metroroomjpc.model.LineaRepository
import net.azarquiel.metroroomjpc.model.LineaWithEstaciones


class LineaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: LineaRepository = LineaRepository(application)

    fun getAllLineas(): LiveData<List<LineaWithEstaciones>> {
        return repository.getAllLineas()
    }
}
