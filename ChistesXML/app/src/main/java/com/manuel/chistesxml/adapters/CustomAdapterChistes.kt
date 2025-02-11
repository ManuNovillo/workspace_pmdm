package com.manuel.chistesxml.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuel.chistesxml.R
import com.manuel.chistesxml.model.Chiste
import com.squareup.picasso.Picasso

class CustomAdapterChistes(val context: Context,
                    val layout: Int
) : RecyclerView.Adapter<CustomAdapterChistes.ViewHolder>() {

    private var dataList: List<Chiste> = emptyList()

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

    internal fun setChistes(chistes: List<Chiste>) {
        this.dataList = chistes
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Chiste){
            val chistesCellImage: ImageView = itemView.findViewById(R.id.chistesCellImage)
            val chistesCellText : TextView = itemView.findViewById(R.id.chistesCellText)
            chistesCellText.text =
                if (dataItem.contenido.length > 20)
                    dataItem.contenido.substring(0, 10)
                else
                    dataItem.contenido
            Picasso.get()
                .load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.idcategoria}.png")
                .into(chistesCellImage)
            itemView.tag = dataItem
        }
    }
}
