package dev.bogibek.nutritionxorazm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.FoodsModel

class FoodsContentAdapter(val foods:ArrayList<FoodsModel>):RecyclerView.Adapter<FoodsContentAdapter.FoodsViewHolder>() {
    class FoodsViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name:TextView = view.findViewById(R.id.tvFoodName)
        val calorie:TextView = view.findViewById(R.id.tvCalorie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder  = FoodsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_history_foods,parent,false)
    )

    override fun getItemCount(): Int  = foods.size

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.apply {
            name.text = foods[position].name
            calorie.text = foods[position].calorie
        }
    }
}