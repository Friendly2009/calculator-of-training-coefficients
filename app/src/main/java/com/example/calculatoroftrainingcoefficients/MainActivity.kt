package com.example.calculatoroftrainingcoefficients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatoroftrainingcoefficients.ui.theme.CalculatorOfTrainingCoefficientsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorOfTrainingCoefficientsTheme {
                Surface(Modifier.fillMaxSize()) {
                    Main()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Calculate",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text("")
            Divider(
                thickness = 2.dp,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(
                text = "5.00000",
                style = MaterialTheme.typography.titleLarge)
        }

        RowButton("2")
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("3")
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("4")
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("5")
        Row() {
            Button(
                onClick = {},
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.size(width = 175.dp, height = 90.dp).padding(top = 40.dp, end = 5.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Del",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Button(
                onClick = {},
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.size(width = 183.dp, height = 90.dp).padding(top = 40.dp, start = 16.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "=",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
@Composable
fun RowButton(index: String){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = index,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x0.1",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline)
                    )
                }
            }
        }

        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = index,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline)
                    )
                }
            }
        }
        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = index,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1.3",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline)
                    )
                }
            }
        }
        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = index,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1.5",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline)
                    )
                }
            }
        }
        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.size(width = 60.dp, height = 60.dp),
            shape = RoundedCornerShape(15.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = index,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.alignBy(FirstBaseline)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x2",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline)
                    )
                }
            }
        }
    }
}