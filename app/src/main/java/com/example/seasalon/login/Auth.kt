package com.example.seasalon.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Auth {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun registerUser(email: String, password: String, user: User, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: ""
                firestore.collection("Users").document(userId).set(user.copy(userId = userId)).addOnCompleteListener {
                    onComplete(it.isSuccessful)
                }
            } else {
                onComplete(false)
            }
        }
    }

    fun loginUser(email: String, password: String, onComplete: (Boolean, User?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: ""
                firestore.collection("Users").document(userId).get().addOnSuccessListener { document ->
                    val user = document.toObject(User::class.java)
                    onComplete(true, user)
                }
            } else {
                onComplete(false, null)
            }
        }
    }
}