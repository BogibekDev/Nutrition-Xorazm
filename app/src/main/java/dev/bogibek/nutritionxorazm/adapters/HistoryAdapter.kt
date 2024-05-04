package dev.bogibek.nutritionxorazm.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.HistoryModel
import dev.bogibek.nutritionxorazm.models.MyHistory

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private val histories: ArrayList<MyHistory> = ArrayList()


    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<MyHistory>) {
        Log.d("@@@@@", "onBindViewHolder: $list")
        histories.clear()
        histories.addAll(list)
        notifyDataSetChanged()
        Log.d("@@@@@", "onBindViewHolder: $histories")
    }

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.tvDate)
        val rvItem: RecyclerView = view.findViewById(R.id.rvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        )

    override fun getItemCount(): Int = histories.size
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        val history = histories[position]
        Log.d("@@@@@", "onBindViewHolder: $history")
        holder.apply {
            date.text = history.date
            rvItem.adapter = HistoryItemAdapter(history.products)
        }
    }
}