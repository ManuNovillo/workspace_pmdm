package com.manuel.agendacontactosjpc.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amigo")
data class Amigo(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var nombre: String = "",
    var telefono: String = "",
    var email: String = ""
)