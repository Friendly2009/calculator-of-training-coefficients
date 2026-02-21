package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatoroftrainingcoefficients.ui.theme.JetBrainsMonoFont

@Composable
fun Element(number: String, coefficient: String){
    Box(
        contentAlignment = Alignment.Center) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = number,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.alignBy(FirstBaseline),
                fontFamily = JetBrainsMonoFont,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "x$coefficient",
                fontSize = 10.sp,
                modifier = Modifier
                    .offset(y = 4.dp)
                    .alignBy(FirstBaseline),
                fontFamily = JetBrainsMonoFont,
            )
        }
    }
}