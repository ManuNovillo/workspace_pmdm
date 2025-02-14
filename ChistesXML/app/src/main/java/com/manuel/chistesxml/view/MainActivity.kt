package com.manuel.chistesxml.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputLayout
import com.manuel.chistesxml.R
import com.manuel.chistesxml.adapters.CustomAdapter
import com.manuel.chistesxml.databinding.ActivityMainBinding
import com.manuel.chistesxml.model.Categoria
import com.manuel.chistesxml.model.Usuario
import com.manuel.chistesxml.viewmodel.DataViewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var titulo = ""
    private lateinit var viewModel: DataViewModel
    private var usuario: Usuario? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        title = titulo
        initRV()
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getCategorias().observe(this) { categoria ->
            categoria?.let {
                adapter.setCategorias(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_user -> {
                // se pulsó sobre el monigote
                dialogLoginRegister()
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
    private fun dialogLoginRegister() {
        val builder = AlertDialog.Builder(this)
        val tvTitle = TextView(this)
        tvTitle.text = "Login & Register"
        tvTitle.setPadding(0,30,0,30)
        tvTitle.gravity = Gravity.CENTER
        tvTitle.textSize = 20.0F
        tvTitle.setTextColor(getColor(R.color.white))
        tvTitle.setBackgroundColor(getColor(R.color.azuloscuro))
        builder.setCustomTitle(tvTitle)
        val ll = LinearLayout(this)
        ll.setPadding(30,30,30,30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.setMargins(0,50,0,50)

        val textInputLayoutNick = TextInputLayout(this)
        textInputLayoutNick.layoutParams = lp
        val etnick = EditText(this)
        etnick.setPadding(0, 80, 0, 80)
        etnick.textSize = 20.0F
        etnick.hint = "Nick"
        textInputLayoutNick.addView(etnick)

        val textInputLayoutPass = TextInputLayout(this)
        textInputLayoutPass.layoutParams = lp
        val etpass = EditText(this)
        etpass.setPadding(0, 80, 0, 80)
        etpass.textSize = 20.0F
        etpass.hint = "Pass"
        textInputLayoutPass.addView(etpass)
        ll.addView(textInputLayoutNick)
        ll.addView(textInputLayoutPass)
        builder.setView(ll)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            login(etnick.text.toString(), etpass.text.toString())
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
        }

        builder.show()
    }

    private fun login(nick: String, pass: String) {
        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getDataUsuarioPorNickPass(nick, pass).observe(this) { usuario ->
            if (usuario != null)
                this.usuario = usuario
            else {
                viewModel = ViewModelProvider(this)[DataViewModel::class.java]
                viewModel.saveUsuario(Usuario("", nick, pass)).observe(this) {
                    this.usuario = it
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                }
            }
            this.usuario?.let {
                title = "$titulo - ${it.nick}"
            }
        }
    }

    override fun onQueryTextChange(query: String): Boolean {
        val original = ArrayList<Categoria>()
        adapter.setBares(original.filter { bar -> bar.nombrebar.contains(query,true) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
// ************* </Filtro> ************

}