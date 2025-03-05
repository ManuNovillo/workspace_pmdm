package net.azarquiel.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.retrofit.api.MainRepository

// ……

/**
* Created by Paco Pulido.
*/
class DataViewModel : ViewModel() {

   private var repository: MainRepository = MainRepository()

   fun getDataBares(): MutableLiveData<List<Bar>> {
       val bares = MutableLiveData<List<Bar>>()
       GlobalScope.launch(Main) {
           bares.value = repository.getDataBares()
       }
       return bares
   }

   fun saveBar( nombrebar: String,direccion: String,
       municipio: String,provincia: String):MutableLiveData<Bar> {
       val bar= MutableLiveData<Bar>()
       GlobalScope.launch(Main) {
           bar.value = repository.saveBar(nombrebar, direccion, municipio, provincia)
       }
       return bar
   }
// ……..   resto de métodos del ViewModel ………..
}
