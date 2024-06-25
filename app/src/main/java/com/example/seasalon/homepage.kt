package com.example.seasalon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.seasalon.ui.theme.DarkBlue
import com.example.seasalon.ui.theme.SEASalonTheme

@Composable
fun homepage(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 16.dp, start = 4.dp, top = 8.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
            ){
                Text(
                    text = "Selamat Datang di SEA Salon!",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 26.sp,
                    ),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Beauty and Elegance Redefined",
                modifier = Modifier
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 20.sp,
                ),
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(30.dp))
            Text(
                text = "Services :" +
                        "\n- Haircuts and Styling" +
                        "\n- Manicure and Pedicure" +
                        "\n- Facial Treatments",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Start,
            )
            Spacer(Modifier.height(60.dp))
            Text(
                text = "Contact Details:\n" +
                        "- Thomas\n" +
                        "Phone Number: 08123456789\n" +
                        "- Sekar\n" +
                        "Phone Number: 08164829372\n",
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                textAlign = TextAlign.Start,
            )

            Button(
                onClick = { navController.navigate("reservasi") },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
            ) {
                Text(
                    text = "Buat Reservasi",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("reviewscreen") },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue),
            ) {
                Text(
                    text = "Berikan Review",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun homepagePreview() {
    SEASalonTheme() {
        val navController = rememberNavController()
        homepage(navController)
    }
}