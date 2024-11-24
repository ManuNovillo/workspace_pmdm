package com.manuel.pueblosbonitos.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "comunidad"
)
data class Comunidad(
    @PrimaryKey
    var id: Int = 0,
    var nombre: String = ""
)
