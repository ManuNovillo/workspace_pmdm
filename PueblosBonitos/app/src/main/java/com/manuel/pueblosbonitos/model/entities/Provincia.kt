package com.manuel.pueblosbonitos.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "provincia",
    foreignKeys = [
        ForeignKey(
            entity = Comunidad::class,
            parentColumns = ["id"],
            childColumns = ["comunidad"])
    ]
)
data class Provincia(
    @PrimaryKey
    var id: Int,
    var nombre: String = "",
    var comunidad: Int = 0
)
