package com.manuel.nbateams

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.manuel.nbateams.adapter.CustomAdapter
import com.manuel.nbateams.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initRV()
        getData()
    }

    private fun getData() {
        GlobalScope.launch {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/teams").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val result = Gson().fromJson(jsontxt, Array<Team>::class.java)
                val teams = result.toList()
                adapter.setTeams(teams)
            }
        }
    }

    private fun initRV() {
        val teamsRV = findViewById<RecyclerView>(R.id.teamsRV)
        adapter = CustomAdapter(this, R.layout.row_teams)
        teamsRV.adapter = adapter
        teamsRV.layoutManager = LinearLayoutManager(this)


    }

}