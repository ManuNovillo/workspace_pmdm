package com.manuel.chistesxml.view

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel.chistesxml.R
import com.manuel.chistesxml.adapters.CustomAdapterChistes
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Chiste
import com.manuel.chistesxml.model.Punto
import com.manuel.chistesxml.viewmodel.DataViewModel
import com.squareup.picasso.Picasso

class DetalleChisteActivity : AppCompatActivity() {
    private lateinit var viewModel: DataViewModel
    private lateinit var avgRBDetail: RatingBar
    private lateinit var puntuarRBDetail: RatingBar
    private lateinit var chisteTVDetail: TextView
    private lateinit var categoriaTVDetail: TextView
    private lateinit var categoriaIVDetail: ImageView
    private lateinit var chiste: Chiste
    private lateinit var categoria: Categoria
    private lateinit var adapter: CustomAdapterChistes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_chiste)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chiste = intent.getSerializableExtra("chiste") as Chiste
        categoria = intent.getSerializableExtra("categoria") as Categoria
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        findView()
        getAvg()
        showData()
    }

    fun getAvg() {
        viewModel.getAvgChiste(chiste.id).observe(this) {
            avgRBDetail.rating = it.toFloat()
        }
    }

    private fun showData() {
        Picasso.get()
            .load("http://www.ies-azarquiel.es/paco/apichistes/img/${categoria.id}.png")
            .into(categoriaIVDetail)
        categoriaTVDetail.text = categoria.nombre
        chisteTVDetail.text = Html.fromHtml(chiste.contenido)
    }

    fun findView() {
        categoriaIVDetail = findViewById(R.id.categoriaIVDetail)
        categoriaTVDetail = findViewById(R.id.categoriaTVDetail)
        chisteTVDetail = findViewById(R.id.chisteTVDetail)
        avgRBDetail = findViewById(R.id.avgRBDetail)
        chisteTVDetail = findViewById(R.id.chisteTVDetail)
        puntuarRBDetail = findViewById(R.id.puntuarRBDetail)
        puntuarRBDetail.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (fromUser) {
                Log.d("MANUEL", "$rating")
                viewModel.savePuntos(chiste.id, Punto("1", chiste.id, rating.toString())).observe(this) {
                    it?.let {
                        Toast.makeText(this, "Puntuado", Toast.LENGTH_SHORT).show()
                        getAvg()
                    }
                }
            }
        }
    }
}