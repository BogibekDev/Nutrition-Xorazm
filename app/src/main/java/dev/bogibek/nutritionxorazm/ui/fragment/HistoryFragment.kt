package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.bogibek.nutritionxorazm.adapters.HistoryAdapter
import dev.bogibek.nutritionxorazm.databinding.FragmentHistoryBinding
import dev.bogibek.nutritionxorazm.models.FoodsModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    lateinit var foods: ArrayList<FoodsModel>
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
        foods = ArrayList()
        binding.apply {
            historyAdapter = HistoryAdapter(foods,requireContext())
            rvHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvHistory.adapter = historyAdapter

        }
    }
}