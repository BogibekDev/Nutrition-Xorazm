package dev.bogibek.nutritionxorazm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.FoodsModel

class FoodsContentAdapter(private val ctx:Context, private val foods: ArrayList<FoodsModel>) :
    RecyclerView.Adapter<FoodsContentAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvFoodsContent: RecyclerView = view.findViewById(R.id.rvFoodsContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history_foods_content, parent, false)
        )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.rvFoodsContent.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)
        holder.rvFoodsContent.adapter = FoodsAdapter(foods)
    }
}