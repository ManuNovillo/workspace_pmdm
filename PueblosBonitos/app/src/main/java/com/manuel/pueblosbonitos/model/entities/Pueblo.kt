package com.manuel.pueblosbonitos.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pueblo",
    foreignKeys = [
        ForeignKey(
            entity = Provincia::class,
            parentColumns = ["id"],
            childColumns = ["provincia"]
        )
    ]
)
data class Pueblo(
    @PrimaryKey
    var id: Int,
    var nombre: String = "",
    var imagen: String = "",
    var link: String = "",
    var fav: Int = 0,
    var provincia: Int = 0
)
