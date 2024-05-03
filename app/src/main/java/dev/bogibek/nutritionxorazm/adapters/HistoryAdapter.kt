package dev.bogibek.nutritionxorazm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.DataItem
import dev.bogibek.nutritionxorazm.models.HistoryModel
import dev.bogibek.nutritionxorazm.models.ProductsItem

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val data: ArrayList<DataItem> = ArrayList()
    private val history: ArrayList<HistoryModel> = ArrayList()
    private val productsItem: ArrayList<ProductsItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<HistoryModel>) {
        this.history.clear()
        this.history.addAll(list)
        notifyDataSetChanged()
    }

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.tvDate)
        val name: TextView = view.findViewById(R.id.tvFood)
        val calorie: TextView = view.findViewById(R.id.tvFoodsCalorie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        )

    override fun getItemCount(): Int = history.size
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.apply {
            date.text = history[position].data?.get(position)?.date
            name.text = history[position].data?.get(position)?.products?.get(position)?.name
            calorie.text = "${history[position].data?.get(position)?.products?.get(position)?.total} kkal"
        }
    }
}