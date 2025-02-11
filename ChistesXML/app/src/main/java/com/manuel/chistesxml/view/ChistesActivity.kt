package com.manuel.chistesxml.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manuel.chistesxml.R
import com.manuel.chistesxml.adapters.CustomAdapterChistes
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Chiste
import com.manuel.chistesxml.viewmodel.DataViewModel

class ChistesActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapterChistes
    private lateinit var categoria: Categoria
    private lateinit var chistesRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chistesRecyclerView = findViewById(R.id.chistesRecyclerView)
        initRV()
        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        categoria = intent.getSerializableExtra("categoria") as Categoria
        viewModel.getChistesByCategoria(categoria.id).observe(this) { chistes ->
            chistes?.let {
                adapter.setChistes(it)
            }
        }
    }
    private fun initRV() {
        adapter = CustomAdapterChistes(this, R.layout.row_chistes)
        if (chistesRecyclerView == null) Log.d("Manu", "NUll")
        chistesRecyclerView.adapter = adapter
        chistesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun onClickChiste(v: View) {
        val chiste = v.tag as Chiste
        val intent = Intent(this, DetalleChisteActivity::class.java)
        intent.putExtra("chiste", chiste)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}