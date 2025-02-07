package com.manuel.chistesxml.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
* Created by Paco Pulido.
* es un object porque solo se crea una vez esta clase
*/


object WebAccess {


   val chistesService : ChistesService by lazy { //servicio que utilizamos


       val retrofit = Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create()) //dios
           .addCallAdapterFactory(CoroutineCallAdapterFactory()) //corrutinas
           .baseUrl("http://www.ies-azarquiel.es/paco/apichistes/") // url y le metemos la ultima / para luego no estar metiendola todo el rato
           .build()


       return@lazy retrofit.create(ChistesService::class.java)
   }
}

