package com.manuel.chistesxml.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.manuel.chistesxml.R
import com.manuel.chistesxml.model.Categoria

class ChistesActivity : AppCompatActivity() {
    private lateinit var categoria: Categoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chistes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) {
        }

        categoria = intent.getSerializableExtra("categoria") as Categoria
    }
}