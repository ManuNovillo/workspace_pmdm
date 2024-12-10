package net.azarquiel.examenmanuelnovillo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Costa::class, Playa::class],
    version = 1
)
abstract class PlayasDB : RoomDatabase() {
    abstract fun daoCosta(): DaoCosta

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: PlayasDB? = null

        fun getDatabase(context: Context): PlayasDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayasDB::class.java, "playasandalu.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
