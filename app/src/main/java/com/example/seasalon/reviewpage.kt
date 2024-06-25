package com.example.seasalon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.seasalon.ui.theme.SEASalonTheme
data class Review(val user: String, val rating: Int, val comment: String)


@Composable
fun ReviewScreen(navController: NavController) {
    var reviews by remember { mutableStateOf(listOf<Review>()) }
    var user by remember { mutableStateOf(TextFieldValue("")) }
    var comment by remember { mutableStateOf(TextFieldValue("")) }
    var rating by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Customer Reviews",
            modifier = Modifier
            .fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Start,)

        Spacer(modifier = Modifier.height(16.dp))

        reviews.forEach { review ->
            ReviewItem(review)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Add a Review",
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = user,
            onValueChange = { user = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Gray)
                .padding(8.dp)
        ) {
            Text(text = if (user.text.isEmpty()) "Enter your name" else user.text)
        }

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = comment,
            onValueChange = { comment = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Gray)
                .padding(8.dp)
        ) {
            Text(text = if (comment.text.isEmpty()) "Enter your review" else comment.text)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            for (i in 1..5) {
                IconButton(onClick = { rating = i }) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (i <= rating) Color.Yellow else Color.Gray
                    )
                }
            }
        }

        Button(onClick = {
            if (user.text.isNotEmpty() && comment.text.isNotEmpty() && rating > 0) {
                reviews = reviews + Review(user.text, rating, comment.text)
                user = TextFieldValue("")
                comment = TextFieldValue("")
                rating = 0
            }
        }) {
            Text("Submit")
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = review.user,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Start,
        )
        Row {
            repeat(review.rating) {
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
            }
        }
        Text(text = review.comment,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Start,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    SEASalonTheme() {
        val navController = rememberNavController()
        ReviewScreen(navController)
    }
}