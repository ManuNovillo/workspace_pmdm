package com.manuel.pueblosbonitos.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PuebloConProvincia(
    @Embedded val pueblo: Pueblo,
    @Relation(
        parentColumn = "provincia",
        entityColumn = "id"
    )
    val provincia: Provincia
)
