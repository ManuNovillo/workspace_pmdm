package net.azarquiel.examenmanuelnovillo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

//data class PuebloConProvincia(
//    @Embedded val pueblo: Pueblo,
//    @Relation(
//        parentColumn = "provincia",
//        entityColumn = "id"
//    )
//    val provincia: Provincia
//)

//@Entity(
//    tableName = "provincia",
//    foreignKeys = [
//        ForeignKey(
//            entity = Comunidad::class,
//            parentColumns = ["id"],
//            childColumns = ["comunidad"])
//    ]
//)
//data class Provincia(
//    @PrimaryKey
//    var id: Int,
//    var nombre: String = "",
//    var comunidad: Int = 0
//)
