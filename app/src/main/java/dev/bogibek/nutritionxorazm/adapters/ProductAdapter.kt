package dev.bogibek.nutritionxorazm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.models.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var list: ArrayList<Product> = ArrayList()
    var itemClick: ((Product) -> Unit)? = null

    fun submitList(list: ArrayList<Product>) {
        this.list.clear()
        this.list.addAll( list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.apply {
            Glide.with(item)
                .load(product.photo)
                .placeholder(R.drawable.img)
                .into(item)
            item.setOnClickListener {
                itemClick?.invoke(product)
            }
        }
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view.findViewById<ImageView>(R.id.ivFood)
    }
}