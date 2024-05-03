package dev.bogibek.nutritionxorazm.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.adapters.ProductAdapter
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.databinding.FragmentFoodsBinding
import dev.bogibek.nutritionxorazm.models.FoodListResponse
import dev.bogibek.nutritionxorazm.models.History
import dev.bogibek.nutritionxorazm.models.Product
import dev.bogibek.nutritionxorazm.models.Responses
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsFragment : Fragment() {
    private lateinit var dialog: Dialog
    private lateinit var binding: FragmentFoodsBinding
    private lateinit var loading: LottieAnimationView
    private lateinit var foodAdapter: ProductAdapter
    private lateinit var saladAdapter: ProductAdapter
    private lateinit var desertAdapter: ProductAdapter
    private lateinit var drinkAdapter: ProductAdapter
    private lateinit var fruitAdapter: ProductAdapter
    private var userId: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = view.findViewById(R.id.loading)
        loading.hide()
        initViews()
    }

    private fun initViews() {
        userId = SharedPrefs(requireContext()).getUserId()
        dialog = Dialog(requireContext())
        initAdapters()
        getProductsList()
        val type = arguments?.getString("type") ?: ""
        binding.apply {
            tvHeader.text = type
            rvFoods.adapter = foodAdapter
            rvShirinlik.adapter = desertAdapter
            rvSalads.adapter = saladAdapter
            rvFruits.adapter = fruitAdapter
            rvIchimlik.adapter = drinkAdapter
        }
    }

    private fun initAdapters() {
        foodAdapter = ProductAdapter()
        saladAdapter = ProductAdapter()
        desertAdapter = ProductAdapter()
        drinkAdapter = ProductAdapter()
        fruitAdapter = ProductAdapter()
        foodAdapter.itemClick = {
            showDialog(it)
        }
        saladAdapter.itemClick = {
            showDialog(it)
        }
        desertAdapter.itemClick = {
            showDialog(it)
        }
        drinkAdapter.itemClick = {
            showDialog(it)
        }
        fruitAdapter.itemClick = {
            showDialog(it)
        }
    }

    private fun showDialog(product: Product) {
        dialog.setContentView(R.layout.dialog_add_product)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val bSave = dialog.findViewById<Button>(R.id.btnSaveVolume)
        val bCancel = dialog.findViewById<Button>(R.id.btnCancelVolume)
        val etVolume = dialog.findViewById<EditText>(R.id.etVolume)
        val tvFoodName = dialog.findViewById<TextView>(R.id.tvFoodName)
        val tvDesc = dialog.findViewById<TextView>(R.id.tvDesc)
        val tvDose = dialog.findViewById<TextView>(R.id.tvDose)

        tvFoodName.text = product.name
        tvDesc.text = product.description
        tvDose.text = getDose(product.dose)

        bCancel.setOnClickListener {
            dialog.dismiss()
        }
        bSave.setOnClickListener {
            val valume = etVolume.text.toString().trim()
            if (valume.isNotBlank()) {
                loading.show()
                ApiClient.apiService.addHistory(userId, History(product.id.toInt(), valume.toDouble()))
                    .enqueue(object :Callback<Responses<Any>>{
                        override fun onResponse(
                            call: Call<Responses<Any>>,
                            response: Response<Responses<Any>>
                        ) {
                            if (response.isSuccessful){
                                if (response.body()!!.success){
                                    Toast.makeText(requireContext(), "Muvafaqqiyatli saqlandi", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(requireContext(), "Nimadir xato ketdi", Toast.LENGTH_SHORT).show()

                                }
                            } else {
                                Toast.makeText(requireContext(), "Nimadir xato ketdi", Toast.LENGTH_SHORT).show()
                            }
                            Log.d("@@@@@", "onResponse: body: ${response.body()}")
                            Log.d("@@@@@", "onResponse: code: ${response.code()}")
                            Log.d("@@@@@", "onResponse: message: ${response.message()}")
                            loading.hide()
                            dialog.dismiss()
                        }

                        override fun onFailure(call: Call<Responses<Any>>, t: Throwable) {
                            loading.hide()
                            Log.d("@@@@@", "onFailure: ${t.message}")
                        }
                    })
            } else {
                Toast.makeText(requireContext(), "Maydonni to'ldiring", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()
    }

    private fun getDose(dose: String): String {
        return when (dose) {
            "portion" -> "Porssi"
            "piece" -> "Donasi"
            "gram" -> "Grami"
            "milliliter" -> "Millilitr"
            else -> ""
        }
    }

    private fun getProductsList() {
        loading.show()
        ApiClient.apiService.getProducts().enqueue(object : Callback<Responses<FoodListResponse>> {
            override fun onResponse(
                call: Call<Responses<FoodListResponse>>,
                response: Response<Responses<FoodListResponse>>
            ) {
                if (response.isSuccessful) {
                    foodAdapter.submitList(response.body()!!.data.foods)
                    saladAdapter.submitList(response.body()!!.data.salads)
                    drinkAdapter.submitList(response.body()!!.data.drinks)
                    desertAdapter.submitList(response.body()!!.data.desserts)
                    fruitAdapter.submitList(response.body()!!.data.fruits)
                }
                Log.d("@@@@@", "onResponse: ${response.body()}")
                loading.hide()
            }

            override fun onFailure(call: Call<Responses<FoodListResponse>>, t: Throwable) {
                loading.hide()
            }
        })
    }

}