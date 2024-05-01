package dev.bogibek.nutritionxorazm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.FoodsModel

class HistoryAdapter(private val foods: ArrayList<FoodsModel>,private val ctx:Context) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvHistory: RecyclerView = view.findViewById(R.id.rvHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history_foods_content, parent, false)
        )

    override fun getItemCount(): Int = 1
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.rvHistory.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)
        holder.rvHistory.adapter = FoodsContentAdapter(ctx,foods)
    }
}