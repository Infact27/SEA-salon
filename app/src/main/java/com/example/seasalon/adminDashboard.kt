package com.example.seasalon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard() {
    var serviceName by remember { mutableStateOf("") }
    var serviceDuration by remember { mutableStateOf("") }

    Column {
        TextField(value = serviceName, onValueChange = { serviceName = it }, label = { Text("Service Name") })
        TextField(value = serviceDuration, onValueChange = { serviceDuration = it }, label = { Text("Service Duration") })
        Button(onClick = {
            val service = Service(name = serviceName, duration = serviceDuration.toInt())
            FirebaseFirestore.getInstance().collection("Services").add(service)
        }) {
            Text("Add Service")
        }
    }
}