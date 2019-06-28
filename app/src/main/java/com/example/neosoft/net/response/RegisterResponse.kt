package com.example.neosoft.net.response

data class RegisterResponse(
    var status:Int = 0,
    var message:String = "",
    var data : RegistrationData
)

data class RegistrationData(
    var id:Int,
    var first_name:String,
    var last_name :String,
    var email : String,
    var password :String,
    var confirmpassword :String,
    var phonenumber : Int
)
