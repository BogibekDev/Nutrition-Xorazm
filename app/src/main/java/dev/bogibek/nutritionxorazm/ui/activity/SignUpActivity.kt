package dev.bogibek.nutritionxorazm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.databinding.ActivitySignUpBinding
import dev.bogibek.nutritionxorazm.models.Responses
import dev.bogibek.nutritionxorazm.models.User
import dev.bogibek.nutritionxorazm.models.UserRequest
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private lateinit var loading: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        loading = findViewById(R.id.loading)
        loading.hide()
        binding.apply {
            tvSignIn.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            }
            btnSignUp.setOnClickListener {
                loading.show()
                val username = etName.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val user = UserRequest(username = username, password = password)
                ApiClient.apiService.personcheck(user).enqueue(object : Callback<Responses<User>> {
                    override fun onResponse(
                        call: Call<Responses<User>>,
                        response: Response<Responses<User>>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()!!.message == "userdoesnotexist") {
                                val intent =
                                    Intent(this@SignUpActivity, UserFieldActivity::class.java)
                                intent.putExtra("username", username)
                                intent.putExtra("password", password)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Bu username avval foydalanilgan\nIltimos boshqa username tanlang!",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                        loading.hide()
                        Log.d("@@@@@", "onResponse: $response")
                    }

                    override fun onFailure(call: Call<Responses<User>>, t: Throwable) {
                        Log.d("@@@@@", "onFailure: ${t.message}")
                        loading.hide()
                    }
                })
            }

        }
    }
}