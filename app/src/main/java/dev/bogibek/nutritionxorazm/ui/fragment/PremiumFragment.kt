package dev.bogibek.nutritionxorazm.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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
        val plan = SharedPrefs(requireContext()).getString("plan") ?: "free"
        val btnFree = view.findViewById<Button>(R.id.btnFree)
        val btnMonth = view.findViewById<Button>(R.id.btnMonth)
        val btnYear = view.findViewById<Button>(R.id.btnYear)
        when (plan) {
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
        btnFree.setOnClickListener {
            Toast.makeText(requireContext(), "All ready selected", Toast.LENGTH_SHORT).show()
        }

        btnMonth.setOnClickListener {
            showDialog(60000)
        }

        btnYear.setOnClickListener {
            showDialog(500000)
        }

    }

    private fun showDialog(money: Int) {
        dialog.setContentView(R.layout.dialog_add_card)
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
        val bPay = dialog.findViewById<Button>(R.id.bPay)
        val ivClose = dialog.findViewById<ImageView>(R.id.ivClose)
        val tvCardNumber = dialog.findViewById<TextView>(R.id.tvCardNumber)
        val tvCardDate = dialog.findViewById<TextView>(R.id.tvDate)
        val tvCardYear = dialog.findViewById<TextView>(R.id.tvYear)
        val tvCardName = dialog.findViewById<TextView>(R.id.tvCardName)
        val etCardName = dialog.findViewById<EditText>(R.id.etCardName)
        val etCardNumber = dialog.findViewById<EditText>(R.id.etCardNumber)
        val etCardDate = dialog.findViewById<EditText>(R.id.etCardDate)
        val etCardYear = dialog.findViewById<EditText>(R.id.etCardYear)

        bPay.text = "$money so'm to'lash"
        ivClose.setOnClickListener{
            dialog.dismiss()
        }
        bPay.setOnClickListener {
            Toast.makeText(requireContext(), "Payme bilan Integratsiya ishlari ketyapdi", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        etCardName.addTextChangedListener {
            tvCardName.text = it
        }
        etCardDate.addTextChangedListener {
            tvCardDate.text = it
        }
        etCardYear.addTextChangedListener {
            tvCardYear.text = it
        }


        etCardNumber.addTextChangedListener {
            if (it?.length == 4 || it?.length == 9 || it?.length == 14) it.append(" ")
            tvCardNumber.text = it
        }

    }




}