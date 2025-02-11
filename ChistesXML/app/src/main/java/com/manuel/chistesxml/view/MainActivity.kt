package com.manuel.chistesxml.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manuel.chistesxml.R
import com.manuel.chistesxml.adapters.CustomAdapter
import com.manuel.chistesxml.databinding.ActivityMainBinding
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        initRV()
        val viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getCategorias().observe(this) { categoria ->
            categoria?.let {
                adapter.setCategorias(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_user -> {
                // se pulsó sobre el monigote
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.row_categoria)
        binding.cm.categoriasRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cm.categoriasRecyclerView.adapter = adapter
    }

    fun onClickCategoria(v: View) {
        val categoria = v.tag as Categoria
        val intent = Intent(this, ChistesActivity::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}