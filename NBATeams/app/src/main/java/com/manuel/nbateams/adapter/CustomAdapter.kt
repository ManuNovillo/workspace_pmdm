package com.manuel.nbateams.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuel.nbateams.R
import com.manuel.nbateams.model.Team
import com.squareup.picasso.Picasso

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTeams(teams: List<Team>) {
        this.dataList = teams
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Team){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val shieldIV = itemView.findViewById(R.id.shieldIV) as ImageView
            val teamTV = itemView.findViewById(R.id.teamTV) as TextView
            //....

            teamTV.text = dataItem.name
            //....

            // foto de internet a traves de Picasso
            Picasso.get().load(dataItem.teamLogoUrl).into(shieldIV)

            itemView.tag = dataItem

        }

    }
}