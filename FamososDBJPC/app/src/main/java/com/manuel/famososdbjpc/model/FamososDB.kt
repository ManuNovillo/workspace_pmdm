package com.manuel.famososdbjpc.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuel.famososdbjpc.dao.CategoriaDao
import com.manuel.famososdbjpc.dao.FamosoDao

@Database(entities = [Famoso::class, Categoria::class], version = 1)
abstract class FamososDB: RoomDatabase() {
    abstract fun famosoDao(): FamosoDao
    abstract fun categoriaDao(): CategoriaDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  FamososDB? = null


        fun getDatabase(context: Context): FamososDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FamososDB::class.java,   "famosos.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
