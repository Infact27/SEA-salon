package com.example.seasalon.login

import androidx.lifecycle.ViewModel
import java.util.UUID

class AuthViewModel : ViewModel() {
    private val authRepository = Auth()

    fun registerUser(email: String, password: String, fullName: String, phoneNumber: String, onComplete: (Boolean) -> Unit) {
        val user = User(
            userId = UUID.randomUUID().toString(),
            fullName = fullName,
            email = email,
            phoneNumber = phoneNumber,
            role = "Customer"
        )
        authRepository.registerUser(email, password, user, onComplete)
    }

    fun loginUser(email: String, password: String, onComplete: (Boolean, User?) -> Unit) {
        authRepository.loginUser(email, password, onComplete)
    }
}