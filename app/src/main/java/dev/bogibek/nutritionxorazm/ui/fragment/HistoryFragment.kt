package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.bogibek.nutritionxorazm.adapters.HistoryAdapter
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.databinding.FragmentHistoryBinding
import dev.bogibek.nutritionxorazm.models.DataItem
import dev.bogibek.nutritionxorazm.models.HistoryModel
import dev.bogibek.nutritionxorazm.models.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var histories: ArrayList<HistoryModel>
    private lateinit var data: ArrayList<DataItem>
    private lateinit var weekly:ArrayList<HistoryModel>
    private lateinit var productsItem: ArrayList<ProductsItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        histories = ArrayList()
        data = ArrayList()
        productsItem = ArrayList()
        historyAdapter = HistoryAdapter()
        binding.apply {
            rvHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHistory.adapter = historyAdapter
            loadHistory()
            historyAdapter.submitList(histories)
        }
    }

    private fun loadHistory() {
        ApiClient.apiService.getHistoryDate().enqueue(object : Callback<HistoryModel>{

            override fun onResponse(call: Call<HistoryModel>, response: Response<HistoryModel>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<HistoryModel>, t: Throwable) {
                Log.d("onFail", "onFailure:$t ")
            }


        })

        ApiClient.apiService.getHistoryWeekly().enqueue(object :Callback<HistoryModel>
        {
            override fun onResponse(call: Call<HistoryModel>, response: Response<HistoryModel>) {
                if (response.isSuccessful){
                    getWeekly(response.body()!!)
                }
            }

            override fun onFailure(call: Call<HistoryModel>, t: Throwable) {
            }

        })
    }

    private fun getWeekly(body: HistoryModel) {
        binding.apply {
            tvWeeklyKKalAmount.text = "${body.total} kkal"
        }
    }
}