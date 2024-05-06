package dev.bogibek.nutritionxorazm.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.models.User
import dev.bogibek.nutritionxorazm.models.UserData
import dev.bogibek.nutritionxorazm.ui.activity.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var dialog: Dialog
    private var userId: Long = 1
    private var height: Double = 0.0
    private var weight: Double = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        dialog = Dialog(requireContext())
        userId = SharedPrefs(requireContext()).getUserId()
        height = (SharedPrefs(requireContext()).getString("height") ?: "0.0").toDouble()
        weight = (SharedPrefs(requireContext()).getString("weight") ?: "0.0").toDouble()
        val btnAbout = view.findViewById<Button>(R.id.ibtnAbout)
        val btnLan = view.findViewById<Button>(R.id.ibtnLanguage)
        val btnRating = view.findViewById<Button>(R.id.ibtnComment)
        val btnCall = view.findViewById<Button>(R.id.ibtnCalling)
        val btnWeight = view.findViewById<Button>(R.id.ibtnWeight)
        val btnHeight = view.findViewById<Button>(R.id.ibtnHeight)
        val btnWeekly = view.findViewById<Button>(R.id.ibtnWeekly)
        val btnLogOut = view.findViewById<Button>(R.id.ibtnLogOut)
        val tvUserName = view.findViewById<TextView>(R.id.tvUsername)
        tvUserName.text = SharedPrefs(requireContext()).getString("username") ?: "Profile"
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
            etVolume.setText(weight.toString())
            bCancel.setOnClickListener {
                dialog.dismiss()
            }
            bSave.setOnClickListener {
                val volume = etVolume.text.toString()
                if (volume.isNotBlank()) {
                    ApiClient.apiService.updatePersonField(userId, UserData(volume.toDouble()))
                        .enqueue(object : Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Muvafaqqiyatli saqlandi",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Nimadur xatolik bo'ldi",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                dialog.dismiss()
                            }

                            override fun onFailure(call: Call<User>, t: Throwable) {
                                dialog.dismiss()
                            }
                        })
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
            etVolume.setText(height.toString())
            bCancel.setOnClickListener {
                dialog.dismiss()
            }
            bSave.setOnClickListener {
                val volume = etVolume.text.toString()
                if (volume.isNotBlank()) {
                    ApiClient.apiService.updatePersonField(
                        userId,
                        UserData(height = volume.toDouble())
                    ).enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    "Muvafaqqiyatli saqlandi",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Nimadur xatolik bo'ldi",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            dialog.dismiss()
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            dialog.dismiss()
                        }
                    })
                    dialog.dismiss()
                }
            }
        }
        btnWeekly.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_weeklyFragment)
        }
        btnLogOut.setOnClickListener {
            SharedPrefs(requireContext()).clear()
            startActivity(Intent(requireContext(),SignInActivity::class.java))
        }
    }

    private fun showDialog(layout: Int) {
        dialog.setContentView(layout)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

}