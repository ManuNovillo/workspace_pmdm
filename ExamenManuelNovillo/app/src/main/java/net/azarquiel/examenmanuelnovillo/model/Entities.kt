package net.azarquiel.examenmanuelnovillo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "costa",
)
data class Costa(
    @PrimaryKey
    var id: Int = 0,
    var nombre: String = "",
    var imagen: String = "",
    var descripcion: String = ""

)

@Entity(
    tableName = "playa",
    foreignKeys = [
        ForeignKey(
            entity = Costa::class,
            parentColumns = ["id"],
            childColumns = ["costa"]
        )]
)
data class Playa(
    @PrimaryKey
    var id: Int,
    var costa: Int = 0,
    var azul: Int = 0,
    var imagen: String = "",
    var nombre: String = ""

)

data class CostaConPlayas(
    @Embedded val costa: Costa,
    @Relation(
        parentColumn = "id",
        entityColumn = "costa"
    )
    val playas: List<Playa>
)
