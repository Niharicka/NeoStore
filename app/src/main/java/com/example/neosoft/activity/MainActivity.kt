package com.example.neosoft.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.neosoft.R
import com.example.neosoft.net.APIManager
import com.example.neosoft.net.APIService
import com.example.neosoft.net.response.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
    }

    fun onClickLogin(view: View){
        doLogin(email.text.toString(),password.text.toString())
    }

    fun onClickPlus(view:View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    private fun doLogin(email: String, password: String) {


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        var retrofit =  Retrofit.Builder()
            .baseUrl("http://staging.php-dev.in:8844/trainingapp/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(APIService::class.java).login(email, password).enqueue(object :
            Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var res: LoginResponse? = response.body()
                Toast.makeText(this@MainActivity, res?.message, Toast.LENGTH_LONG).show()
            }
        })

    }
}
