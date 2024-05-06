package dev.bogibek.nutritionxorazm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.ProductsItem
import dev.bogibek.nutritionxorazm.utils.show

class HistoryItemAdapter(private val foods: ArrayList<ProductsItem>) :
    RecyclerView.Adapter<HistoryItemAdapter.AdviceViewHolder>() {

    var deleteClick: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceViewHolder =
        AdviceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_advice, parent, false)
        )

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: AdviceViewHolder, position: Int) {
        val food = foods[position]
        holder.apply {
            foodsName.text = food.name
            calorie.text = "${food.total} kkal"
            ivDelete.show()
            ivDelete.setOnClickListener {
                // call api is here
                deleteClick?.invoke(food.id)
            }
        }
    }


    class AdviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodsName: TextView = view.findViewById(R.id.tvFoodName)
        val calorie: TextView = view.findViewById(R.id.tvFoodCalorie)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
    }
}