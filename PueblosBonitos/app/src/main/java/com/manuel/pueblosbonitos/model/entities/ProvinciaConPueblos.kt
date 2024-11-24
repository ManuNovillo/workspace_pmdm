package com.manuel.pueblosbonitos.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProvinciaConPueblos(
    @Embedded val provincia: Provincia,
    @Relation(
        parentColumn = "id",
        entityColumn = "provincia"
    )
    val pueblos: List<Pueblo>
)
