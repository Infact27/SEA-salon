package com.example.seasalon.login

data class User(
    val userId: String = "",
    val fullName: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val role: String = "Customer"
)