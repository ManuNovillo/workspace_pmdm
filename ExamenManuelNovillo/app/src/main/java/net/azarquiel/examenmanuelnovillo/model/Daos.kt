package net.azarquiel.examenmanuelnovillo.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DaoCosta {
    @Transaction
    @Query("SELECT * FROM Costa")
    fun getAllCostas(): LiveData<List<CostaConPlayas>>
}
