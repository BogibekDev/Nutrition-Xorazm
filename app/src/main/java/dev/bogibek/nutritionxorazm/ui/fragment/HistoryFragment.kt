package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.adapters.HistoryAdapter
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.models.HistoryModel
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment(R.layout.fragment_history) {


    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var tvTotal: TextView
    private var userId: Long = 1
    private lateinit var loading: LottieAnimationView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        tvTotal = view.findViewById(R.id.tvWeeklyKKalAmount)
        loading = view.findViewById(R.id.loading)
        loading.hide()
        userId = SharedPrefs(requireContext()).getUserId()
        historyAdapter = HistoryAdapter()
        rvHistory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        loadHistory()
        historyAdapter.deleteHistoryItem = {id->
            deleteProduct(id)
        }
    }

    private fun deleteProduct(id: Long) {
        loading.show()
        ApiClient.apiService.deleteProduct(id).enqueue(object :Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Muvaffaqiyatli o'chirildi", Toast.LENGTH_SHORT).show()
                    loadHistory()
                } else {
                    Toast.makeText(context, "Nimadur xatolik yuz berdi", Toast.LENGTH_SHORT).show()
                    loading.hide()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(context, "Serverda qandaydir xatolik bor!", Toast.LENGTH_SHORT).show()
                loading.hide()
            }
        })
    }

    private fun loadHistory() {
        loading.show()
        ApiClient.apiService.getHistoryDate(userId).enqueue(object : Callback<HistoryModel> {
            override fun onResponse(call: Call<HistoryModel>, response: Response<HistoryModel>) {
                if (response.isSuccessful) {
                    historyAdapter.submitList(response.body()!!.data)
                    tvTotal.text = "${response.body()!!.total} kkall"
                }
                loading.hide()
                Log.d("@@@@@", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<HistoryModel>, t: Throwable) {
                Log.d("onFail", "onFailure:$t ")
                loading.hide()
            }


        })
    }

}