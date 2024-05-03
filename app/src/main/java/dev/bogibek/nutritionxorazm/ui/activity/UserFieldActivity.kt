package dev.bogibek.nutritionxorazm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.databinding.ActivityUserFieldBinding
import dev.bogibek.nutritionxorazm.models.User
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFieldActivity : AppCompatActivity() {
    private val binding by lazy { ActivityUserFieldBinding.inflate(layoutInflater) }
    private lateinit var loading: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }

    private fun initViews() {
        loading = findViewById(R.id.loading)
        loading.hide()
        val username = intent.getStringExtra("username") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        var gender = "male"

        binding.apply {
            btnMale.setBackgroundResource(R.drawable.btn_gender_bg_selected)
            btnFemale.setOnClickListener {
                gender = "female"
                it.setBackgroundResource(R.drawable.btn_gender_bg_selected)
                btnMale.setBackgroundResource(R.drawable.btn_gender_bg)
            }
            btnMale.setOnClickListener {
                gender = "male"
                it.setBackgroundResource(R.drawable.btn_gender_bg_selected)
                btnFemale.setBackgroundResource(R.drawable.btn_gender_bg)
            }
            btnSave.setOnClickListener {
                val age = etAge.text.toString().trim().toDouble()
                val height = etHeight.text.toString().trim().toDouble()
                val weight = etWeight.text.toString().trim().toDouble()
                if (weight>0 && height>0 && age>0){
                    val user = User(
                        username = username,
                        password = password,
                        height = height,
                        weight = weight,
                        gender = gender
                    )
                    loading.show()
                    ApiClient.apiService.createUser(user).enqueue(object :Callback<User>{
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            loading.hide()
                            if (response.isSuccessful){
                                if(response.body()!=null){
                                    SharedPrefs(this@UserFieldActivity).saveUserId(response.body()?.id?:0)
                                    SharedPrefs(this@UserFieldActivity).saveBoolean("IS_SIGNED",true)
                                    SharedPrefs(this@UserFieldActivity).saveString("plan",response.body()?.plan)
                                    startActivity(Intent(this@UserFieldActivity,MainActivity::class.java))
                                    finish()
                                }
                            }

                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            loading.hide()

                        }
                    })
                } else{
                    Toast.makeText(this@UserFieldActivity, "Ma'lumotlarni to'liq kiriting", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}