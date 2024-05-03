package dev.bogibek.nutritionxorazm.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs


class PremiumFragment : Fragment(R.layout.fragment_premium) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val plan = SharedPrefs(requireContext()).getString("plan")?:"free"
        val btnFree = view.findViewById<Button>(R.id.btnFree)
        val btnMonth = view.findViewById<Button>(R.id.btnMonth)
        val btnYear = view.findViewById<Button>(R.id.btnYear)
        when(plan){
            "paid" -> {
                btnMonth.background.setTint(Color.parseColor("#FF7B01"))
                btnFree.background.setTint(Color.parseColor("#08A6B0"))
                btnYear.background.setTint(Color.parseColor("#08A6B0"))
            }
            "premium" -> {
                btnMonth.background.setTint(Color.parseColor("#08A6B0"))
                btnFree.background.setTint(Color.parseColor("#08A6B0"))
                btnYear.background.setTint(Color.parseColor("#FF7B01"))
            }
            else -> {
                btnFree.background.setTint(Color.parseColor("#FF7B01"))
                btnMonth.background.setTint(Color.parseColor("#08A6B0"))
                btnYear.background.setTint(Color.parseColor("#08A6B0"))
            }
        }
        btnMonth.setOnClickListener {
            Toast.makeText(requireContext(), "Not Implemented yet", Toast.LENGTH_SHORT).show()
        }
        btnFree.setOnClickListener {
            Toast.makeText(requireContext(), "Not Implemented yet", Toast.LENGTH_SHORT).show()
        }
        btnYear.setOnClickListener {
            Toast.makeText(requireContext(), "Not Implemented yet", Toast.LENGTH_SHORT).show()
        }

    }

}