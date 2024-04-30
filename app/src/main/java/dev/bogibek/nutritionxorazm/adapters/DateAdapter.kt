package dev.bogibek.nutritionxorazm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.DateModel

class DateAdapter(val ctx:Context,val date:ArrayList<DateModel>):RecyclerView.Adapter<DateAdapter.DateViewHolder>() {
    class DateViewHolder(view:View):RecyclerView.ViewHolder(view){
        val date:TextView = view.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder = DateViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history_date,parent,false)
    )

    override fun getItemCount(): Int  = date.size

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.date.text = date[position].date
    }
}