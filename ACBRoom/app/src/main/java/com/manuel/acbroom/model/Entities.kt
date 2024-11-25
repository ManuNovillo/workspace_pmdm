package com.manuel.acbroom.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "equipo")
data class Equipo(
    @PrimaryKey
    var id: Int = 0,
    var nombre: String = "",
    var imgestadio: String = "",
    var imgescudo: String = "",
)

@Entity(
    tableName = "jugador",
    foreignKeys = [
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo"]
        )
    ]
)
data class Jugador(
    @PrimaryKey
    var id: Int = 0,
    var nombre: String = "",
    var imagen: String = "",
    var equipo: Int = 0,
    var pais: String = "",
    var edad: Int = 0,
    var estatura: Float = 0f,
    var likes: Int = 0,
    var link: String = "",
)

data class EquipoConJugadores(
    @Embedded val equipo: Equipo,
    @Relation(
        parentColumn = "id",
        entityColumn = "equipo"
    )
    val jugadores: List<Jugador>
)

data class JugadorConEquipo(
    @Embedded val jugador: Jugador,
    @Relation(
        parentColumn = "equipo",
        entityColumn = "id"
    )
    val equipo: Equipo
)