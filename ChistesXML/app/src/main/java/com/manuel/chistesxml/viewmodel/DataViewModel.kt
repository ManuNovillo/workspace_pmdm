package com.manuel.chistesxml.viewmodel

import MainRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Chiste
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

   private var repository: MainRepository = MainRepository()

   fun getCategorias(): MutableLiveData<List<Categoria>> {
       val categorias = MutableLiveData<List<Categoria>>()
       GlobalScope.launch(Main) { //envuelto en corrutina, los metodos dentro de ese hilo dentro de await y suspend
           categorias.value = repository.getCategorias() //dentro de livedata .value para acceder al dato
       }
       return categorias
   }

   fun saveChiste(chiste: Chiste):MutableLiveData<Chiste> {
       val chisteResponse = MutableLiveData<Chiste>()
       GlobalScope.launch(Main) {
           chisteResponse.value = repository.saveChiste(chiste)
       }
       return chisteResponse
   }

   fun getChistesByCategoria(idCategoria: String):MutableLiveData<List<Chiste>> {
       val chistes = MutableLiveData<List<Chiste>>()
       GlobalScope.launch(Main) {
           chistes.value = repository.getChistesByCategoria(idCategoria)
       }
       return chistes
   }
}







