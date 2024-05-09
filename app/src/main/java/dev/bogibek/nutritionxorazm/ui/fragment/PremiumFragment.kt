package dev.bogibek.nutritionxorazm.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs


class PremiumFragment : Fragment(R.layout.fragment_premium) {
    private lateinit var dialog: Dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        dialog = Dialog(requireContext())
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
            showDialog(60000)
        }
        btnFree.setOnClickListener {
            Toast.makeText(requireContext(), "All ready selected", Toast.LENGTH_SHORT).show()
        }
        btnYear.setOnClickListener {
            showDialog(500000)
        }

    }

    private fun showDialog(money: Int) {
        dialog.setContentView(R.layout.dialog_add_card)
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog.show()
//        val bSave = dialog.findViewById<Button>(R.id.btnSaveVolume)
//        val bCancel = dialog.findViewById<Button>(R.id.btnCancelVolume)
//        val etVolume = dialog.findViewById<EditText>(R.id.etVolume)
    }

}