package com.example.calculatoroftrainingcoefficients.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatoroftrainingcoefficients.core.MainViewModel
import com.example.calculatoroftrainingcoefficients.ui.theme.JetBrainsMonoFont

@Composable
fun Main(toSupport: () ->  Unit ,viewModel: MainViewModel = viewModel()) {
    val horizontalScrollState = rememberScrollState()
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(60.dp,60.dp)
                .padding(top = 20.dp, end = 20.dp)
                .background(Color.Transparent.copy(alpha = 0.1f), shape = CircleShape)
                .clickable(onClick = { toSupport() }),
            contentAlignment = Alignment.Center
        ) {
            Text("i")
        }
    }
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
            Spacer(Modifier.height(50.dp))
            Text(
                text = "Calculate",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp),
                fontFamily = JetBrainsMonoFont,
                fontWeight = FontWeight.Normal,
            )
            Row(Modifier.horizontalScroll(horizontalScrollState),horizontalArrangement = Arrangement.Start) {
                viewModel.numbersList.forEachIndexed {index, element ->
                    Element(index, element.number, element.coefficient, viewModel)
                }
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color.LightGray
            )
        }

        Box(modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(
                fontFamily = JetBrainsMonoFont,
                fontWeight = FontWeight.Normal,
                text = viewModel.result.value.toString(),
                style = MaterialTheme.typography.titleLarge)
        }

        RowButton("2", viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("3", viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("4", viewModel)
        Spacer(modifier = Modifier.height(40.dp))
        RowButton("5", viewModel)

        Row() {

            Button(
                onClick = { viewModel.calculate()},
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "=",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = JetBrainsMonoFont,
                )
            }
        }
    }
}

@Composable
fun RowButton(index: String,viewModel: MainViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                viewModel.addElement(index, "0.1")
            },
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
                        modifier = Modifier.alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x0.1",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont,
                    )
                }
            }
        }

        Button(
            onClick = {
                viewModel.addElement(index, "1")
            },
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
                        modifier = Modifier.alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                }
            }
        }
        Button(
            onClick = {
                viewModel.addElement(index, "1.3")
            },
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
                        modifier = Modifier.alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1.3",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                }
            }
        }
        Button(
            onClick = {
                viewModel.addElement(index, "1.5")
            },
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
                        modifier = Modifier.alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x1.5",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                }
            }
        }
        Button(
            onClick = {
                viewModel.addElement(index, "2")
            },
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
                        modifier = Modifier.alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "x2",
                        fontSize = 10.sp,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .alignBy(FirstBaseline),
                        fontFamily = JetBrainsMonoFont
                    )
                }
            }
        }
    }
}