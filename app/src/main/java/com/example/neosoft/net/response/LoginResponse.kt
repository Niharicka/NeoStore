package com.example.neosoft.net.response

data class LoginResponse(
    var status:Int = 0,
    var message:String = "",
    var data : LoginData
)

data class LoginData(
    var id:Int,
    var first_name:String
)