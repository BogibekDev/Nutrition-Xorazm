package dev.bogibek.nutritionxorazm.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import dev.bogibek.nutritionxorazm.R


class WeeklyFragment : Fragment(R.layout.fragment_weekly){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val ivBack:ImageView = view.findViewById(R.id.ibBack)
        ivBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}