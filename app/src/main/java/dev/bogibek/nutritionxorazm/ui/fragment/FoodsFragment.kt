package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.bogibek.nutritionxorazm.databinding.FragmentBreakfastBinding
import dev.bogibek.nutritionxorazm.databinding.FragmentFoodsBinding

class FoodsFragment : Fragment() {

    private lateinit var binding:FragmentFoodsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}