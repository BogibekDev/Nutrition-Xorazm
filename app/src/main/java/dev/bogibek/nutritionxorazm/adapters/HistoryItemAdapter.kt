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
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.models.ProductsItem
import dev.bogibek.nutritionxorazm.models.Recommend
import dev.bogibek.nutritionxorazm.models.Responses
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryItemAdapter(val context:Context ,private val foods: ArrayList<ProductsItem>) : RecyclerView.Adapter<HistoryItemAdapter.AdviceViewHolder>() {

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
                val userId = SharedPrefs(context).getUserId()
                ApiClient.apiService.deleteProduct(userId,1).enqueue(object :Callback<Responses<Any>>{
                    override fun onResponse(
                        call: Call<Responses<Any>>,
                        response: Response<Responses<Any>>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(context, "Muvaffaqiyatli o'chirildi", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "nimadur xato", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<Responses<Any>>, t: Throwable) {
                        Toast.makeText(context, "serverda xatolik", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
    class AdviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodsName: TextView = view.findViewById(R.id.tvFoodName)
        val calorie: TextView = view.findViewById(R.id.tvFoodCalorie)
        val ivDelete :ImageView = view.findViewById(R.id.ivDelete)
    }
}