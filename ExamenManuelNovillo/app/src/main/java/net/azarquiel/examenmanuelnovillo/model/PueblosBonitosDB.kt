package net.azarquiel.examenmanuelnovillo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class PueblosBonitosDB : RoomDatabase() {
//    abstract fun daoComunidad(): DAOComunidad
//    abstract fun daoPueblo(): DAOPueblo

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PueblosBonitosDB? = null

        fun getDatabase(context: Context): PueblosBonitosDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PueblosBonitosDB::class.java, "pueblosbonitos.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
