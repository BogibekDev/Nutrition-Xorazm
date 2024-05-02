package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.bogibek.nutritionxorazm.adapters.AdviceAdapter
import dev.bogibek.nutritionxorazm.databinding.FragmentAdviceBinding
import dev.bogibek.nutritionxorazm.models.FoodsModel


class AdviceFragment : Fragment() {
    private lateinit var binding: FragmentAdviceBinding
    private lateinit var foods: ArrayList<FoodsModel>
    private lateinit var adapter: AdviceAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdviceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter = AdviceAdapter(foods)
        foods = ArrayList()
        binding.apply {
            rvAdvice?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvAdvice?.adapter = adapter

        }
    }
}