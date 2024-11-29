package com.manuel.agendacontactosjpc.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuel.agendacontactosjpc.model.daos.AmigoDao
import com.manuel.agendacontactosjpc.model.entities.Amigo


@Database(entities = [Amigo::class], version = 1)
abstract class AgendaDB: RoomDatabase() {
   abstract fun amigoDao(): AmigoDao
   companion object {
       // Singleton prevents multiple instances of database opening at the same time.
       @Volatile
       private var INSTANCE:  AgendaDB? = null

       fun getDatabase(context: Context): AgendaDB {
           val tempInstance = INSTANCE
           if (tempInstance != null) {
               return tempInstance
           }
           synchronized(this) {
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   AgendaDB::class.java,"Agenda.sqlite"
               ).build()
               INSTANCE = instance
               return instance
           }
       }
   }
}
