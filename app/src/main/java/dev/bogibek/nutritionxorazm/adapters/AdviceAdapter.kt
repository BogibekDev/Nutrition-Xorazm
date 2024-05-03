package dev.bogibek.nutritionxorazm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.FoodsModel

class AdviceAdapter(val foods: ArrayList<FoodsModel>) :
    RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder>() {
    class AdviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodsName: TextView = view.findViewById(R.id.tvFoodNameAdvice)
        val calorie: TextView = view.findViewById(R.id.tvCalorieAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceViewHolder =
        AdviceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_advice_foods, parent, false)
        )

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: AdviceViewHolder, position: Int) {
        holder.apply {
            foodsName.text = foods[position].name
            calorie.text = foods[position].calorie
        }
    }
}