package dev.bogibek.nutritionxorazm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import dev.bogibek.nutritionxorazm.R
import dev.bogibek.nutritionxorazm.data.local.SharedPrefs
import dev.bogibek.nutritionxorazm.data.remote.ApiClient
import dev.bogibek.nutritionxorazm.databinding.ActivitySignInBinding
import dev.bogibek.nutritionxorazm.models.Responses
import dev.bogibek.nutritionxorazm.models.User
import dev.bogibek.nutritionxorazm.models.UserRequest
import dev.bogibek.nutritionxorazm.utils.hide
import dev.bogibek.nutritionxorazm.utils.show
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
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

            btnSignIn.setOnClickListener {
                val username = etName.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if(username.isNotBlank() && password.isNotBlank()){
                    val user = UserRequest(username = username, password = password)
                    Log.d("@@@@@", "initViews: $user")
                    loading.show()
                    ApiClient.apiService.personcheck(user).enqueue(object : Callback<Responses<User>> {
                        override fun onResponse(
                            call: Call<Responses<User>>,
                            response: Response<Responses<User>>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                if (response.body()!!.success) {
                                    SharedPrefs(this@SignInActivity).saveUserId(response.body()?.data?.id?:0)
                                    SharedPrefs(this@SignInActivity).saveBoolean("IS_SIGNED",true)
                                    SharedPrefs(this@SignInActivity).saveString("plan",response.body()?.data?.plan)
                                    SharedPrefs(this@SignInActivity).saveString("username",response.body()?.data?.username)
                                    SharedPrefs(this@SignInActivity).saveString("weight",response.body()?.data?.weight.toString())
                                    SharedPrefs(this@SignInActivity).saveString("height",response.body()?.data?.height.toString())

                                    startActivity(Intent(this@SignInActivity,MainActivity::class.java))
                                } else {
                                    Toast.makeText(this@SignInActivity, "Username yoki parol xato", Toast.LENGTH_SHORT).show()
                                }
                            }
                            loading.hide()
                            Log.d("@@@@@", "onResponse: code: ${response.code()}")
                            Log.d("@@@@@", "onResponse: body:  ${response.body()}")
                            Log.d("@@@@@", "onResponse: errorbody:  ${response.errorBody()}")
                            if (response.code() >=500){
                                Toast.makeText(this@SignInActivity, "Server bilan bogliq muammo mavjud", Toast.LENGTH_SHORT).show()
                            }

                        }

                        override fun onFailure(call: Call<Responses<User>>, t: Throwable) {
                            Toast.makeText(this@SignInActivity, "Internet bilan bog'liq muammo mavjud", Toast.LENGTH_SHORT).show()
                            loading.hide()
                        }
                    })

                }
            }
            tvSignIn.setOnClickListener {
                startActivity(Intent(this@SignInActivity,SignUpActivity::class.java))
                finish()
            }
        }
    }
}