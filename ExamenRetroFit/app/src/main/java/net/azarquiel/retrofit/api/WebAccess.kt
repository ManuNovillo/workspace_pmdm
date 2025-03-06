package net.azarquiel.retrofit.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebAccess {

   val gafasService : GafasService by lazy {

       val retrofit = Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(CoroutineCallAdapterFactory())
           .baseUrl("http://www.ies-azarquiel.es/paco/apigafas/")
           .build()

       retrofit.create(GafasService::class.java)
   }
 }