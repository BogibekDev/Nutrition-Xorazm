package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.adapters.AdviceAdapter
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.models.AdviceResponse
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdviceFragment : Fragment(R.layout.fragment_advice) {
    private lateinit var loading: LottieAnimationView
    private lateinit var adapter: AdviceAdapter
    private lateinit var llTabrik: LinearLayout
    private lateinit var llOOPS: LinearLayout
    private lateinit var llAdvice: LinearLayout
    private lateinit var tvCalorie: TextView
    private lateinit var tvInfo: TextView
    private lateinit var tvCalorieOOPS: TextView
    private lateinit var tvHeader: TextView
    private lateinit var rvAdvice: RecyclerView
    private var userId: Long = 1
    private var total = 0.0
    private var need = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        userId = SharedPrefs(requireContext()).getUserId()
        loading = view.findViewById(R.id.loading)
        tvCalorieOOPS = view.findViewById(R.id.tvCalorieOOPS)
        llTabrik = view.findViewById(R.id.llTabrik)
        llOOPS = view.findViewById(R.id.llOOPS)
        llAdvice = view.findViewById(R.id.llAdvice)
        tvHeader = view.findViewById(R.id.tvHeader)
        tvInfo = view.findViewById(R.id.tvInfo)
        rvAdvice = view.findViewById(R.id.rvAdvice)

        loading.hide()
        adapter = AdviceAdapter()
        rvAdvice.adapter = adapter
        getAdviceList()
    }

    private fun getAdviceList() {
        loading.show()
        Log.d("@@@@@", "getAdviceList:$userId ")
        ApiClient.apiService.getAdvice(userId).enqueue(object : Callback<AdviceResponse> {
            override fun onResponse(
                call: Call<AdviceResponse>,
                response: Response<AdviceResponse>
            ) {
                if (response.isSuccessful) {
                    total = response.body()!!.total
                    need = response.body()!!.need
                    adapter.submitList(response.body()!!.recommend)
                    setChanges()

                }
                loading.hide()
            }

            override fun onFailure(call: Call<AdviceResponse>, t: Throwable) {
                loading.hide()
            }
        })
    }

    private fun setChanges() {
        if (need > 0) {
            tvHeader.text="OOPS"
            tvCalorieOOPS.text = need.toString()
            tvInfo.text = "kaloriya to’plolmadingiz!"
        } else {
            tvHeader.text="TABRIKLAYMIZ"
            tvCalorieOOPS.text = total.toString()
            tvInfo.text = "kaloriya to’pladingiz!"
        }
        llOOPS.show()
        llAdvice.show()
    }
}