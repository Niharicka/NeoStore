package com.example.neosoft.net

import com.example.neosoft.net.response.LoginResponse
import com.example.neosoft.net.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {


    @FormUrlEncoded
    @POST("api/users/login")
    fun login(@Field("email")email:String,@Field("password")password:String):Call<LoginResponse>

    @FormUrlEncoded
    @POST("api/users/register")
    fun register(@Field("first_name")firstName:String,lastName:String):Call<RegisterResponse>
}