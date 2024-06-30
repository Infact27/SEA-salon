package com.example.seasalon

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.seasalon.ui.theme.SEASalonTheme
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reservasi(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var serviceType by remember { mutableStateOf("Haircuts and styling") }
    var dateTime by remember { mutableStateOf("") }

    val db = FirebaseFirestore.getInstance()
    val serviceTypes = listOf("Haircuts and styling", "Manicure and pedicure", "Facial treatments")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Active Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Type of Service",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 0.15.sp,
                color = Color.Black
        )
        serviceTypes.forEach { type ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (serviceType == type),
                    onClick = { serviceType = type }
                )
                Text(text = type, modifier = Modifier.padding(start = 8.dp), color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = dateTime,
            onValueChange = { dateTime = it },
            label = { Text("Date and Time") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val reservation = hashMapOf(
                    "name" to name,
                    "phoneNumber" to phoneNumber,
                    "serviceType" to serviceType,
                    "dateTime" to dateTime
                )
                db.collection("reservations").add(reservation)
                navController.navigate("homepage")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun reservasiPreview() {
    SEASalonTheme() {
        val navController = rememberNavController()
        Reservasi(navController)
    }
}