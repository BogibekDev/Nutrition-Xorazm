package dev.bogibek.nutritionxorazm.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout.LayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.bogibek.nutritionxorazm.R


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var dialog: Dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        dialog = Dialog(requireContext())
        val btnAbout = view.findViewById<Button>(R.id.ibtnAbout)
        val btnLan = view.findViewById<Button>(R.id.ibtnLanguage)
        val btnRating = view.findViewById<Button>(R.id.ibtnComment)
        val btnCall = view.findViewById<Button>(R.id.ibtnCalling)
        val btnWeight = view.findViewById<Button>(R.id.ibtnWeight)
        val btnHeight = view.findViewById<Button>(R.id.ibtnHeight)
        val btnWeekly = view.findViewById<Button>(R.id.ibtnWeekly)

        btnAbout.setOnClickListener {
            showDialog(R.layout.dialog_about_program)
        }
        btnLan.setOnClickListener {
            showDialog(R.layout.dialog_language)
        }
        btnRating.setOnClickListener {
            showDialog(R.layout.dialog_review)
        }
        btnCall.setOnClickListener {
            showDialog(R.layout.dialog_call)
        }
        btnWeight.setOnClickListener {
            dialog.setContentView(R.layout.dialog_profile_change_weight)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            dialog.show()
            val bSave = dialog.findViewById<Button>(R.id.btnSaveVolume)
            val bCancel = dialog.findViewById<Button>(R.id.btnCancelVolume)
            val etVolume = dialog.findViewById<EditText>(R.id.etVolume)
            bCancel.setOnClickListener {
                dialog.dismiss()
            }
            bSave.setOnClickListener {
                val volume = etVolume.text.toString()
                if (volume.isNotBlank()){

                    //call api
                    dialog.dismiss()
                }
            }
        }
        btnHeight.setOnClickListener {
            dialog.setContentView(R.layout.dialog_profile_change_height)
            dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            dialog.show()
            val bSave = dialog.findViewById<Button>(R.id.btnSaveVolume)
            val bCancel = dialog.findViewById<Button>(R.id.btnCancelVolume)
            val etVolume = dialog.findViewById<EditText>(R.id.etVolume)
            bCancel.setOnClickListener {
                dialog.dismiss()
            }
            bSave.setOnClickListener {
                val volume = etVolume.text.toString().toDouble()
                if (volume>=0){
                    //call api
                    dialog.dismiss()
                }
            }
        }
        btnWeekly.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_weeklyFragment)
        }
    }

    private fun showDialog(layout: Int) {
        dialog.setContentView(layout)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

}