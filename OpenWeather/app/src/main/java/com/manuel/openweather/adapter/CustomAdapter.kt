package com.manuel.openweather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuel.openweather.R
import com.manuel.openweather.model.Dia
import com.squareup.picasso.Picasso

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Dia> = emptyList()

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

    internal fun setDias(teams: List<Dia>) {
        this.dataList = teams
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Dia){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val cieloIV = itemView.findViewById(R.id.cieloIV) as ImageView
            val precipTV = itemView.findViewById(R.id.precipTV) as TextView
            val cieloStateTV = itemView.findViewById(R.id.cieloStateTV) as TextView
            val minTempTV = itemView.findViewById(R.id.minTempTV) as TextView
            val maxTempTV = itemView.findViewById(R.id.maxTempTV) as TextView
            //....

            precipTV.text = "${dataItem.pop}"
            cieloStateTV.text = dataItem.weather[0].description
            minTempTV.text = "${dataItem.temp.min}"
            maxTempTV.text = "${dataItem.temp.min}"
            //....

            // foto de internet a traves de Picasso
            Picasso.get().load("https://openweathermap.org/img/wn/04n@2x.png").into(cieloIV)
            //Log.d("manu","https://openweathermap.org/img/wn/${dataItem.weather.get(0).icon}@4x.png")
            itemView.tag = dataItem

        }

    }
}