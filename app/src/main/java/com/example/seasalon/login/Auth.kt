package com.example.seasalon.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.mindrot.jbcrypt.BCrypt

class Auth {

    private val auth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, user: User, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid ?: ""
                FirebaseDatabase.getInstance().getReference("Users").child(userId).setValue(user).addOnCompleteListener {
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
                FirebaseDatabase.getInstance().getReference("Users").child(userId).get().addOnSuccessListener {
                    val user = it.getValue(User::class.java)
                    onComplete(true, user)
                }
            } else {
                onComplete(false, null)
            }
        }
    }
}
