package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }
    private fun initViews(view: View) {
        binding.apply {
            ivBreakfast.setOnClickListener {
                val bundle = bundleOf("type" to "Ertalabki nonushta")
                findNavController().navigate(R.id.action_homeFragment_to_breakfastFragment,bundle)
            }
            ivLunch.setOnClickListener {
                val bundle = bundleOf("type" to "Tushlik")
                findNavController().navigate(R.id.action_homeFragment_to_breakfastFragment,bundle)
            }
            ivDinner.setOnClickListener {
                val bundle = bundleOf("type" to "Kechki ovqat")
                findNavController().navigate(R.id.action_homeFragment_to_breakfastFragment,bundle)
            }
        }
    }
}