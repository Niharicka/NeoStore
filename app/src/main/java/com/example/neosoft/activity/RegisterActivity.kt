package com.example.neosoft.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.neosoft.R
import com.example.neosoft.net.APIManager
import com.example.neosoft.net.APIService
import com.example.neosoft.net.response.LoginResponse
import com.example.neosoft.net.response.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var firstname: EditText
    lateinit var lastname: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmpassword: EditText
    lateinit var phonenumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmpassword = findViewById(R.id.confirmpassword)
        phonenumber = findViewById(R.id.phonenumber)

    }

        fun onClickRegister(view: View) {
            doRegister(email.text.toString(), password.text.toString())
        }

        fun doRegister(email: String, password: String) {

            APIManager.provideRetrofit().create(APIService::class.java).register(email, password).enqueue(object :
                Callback<RegisterResponse> {
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Failed", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    var res: RegisterResponse? = response.body()
                    Toast.makeText(this@RegisterActivity, res?.message, Toast.LENGTH_LONG).show()
                }
            })

        }

}