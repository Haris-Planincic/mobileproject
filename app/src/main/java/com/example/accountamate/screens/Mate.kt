package com.example.accountamate.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.accountamate.R
import com.example.accountamate.ui.theme.Green400
import com.example.accountamate.ui.theme.Pinky


@Composable
fun Mate() {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pinky),
        contentAlignment = Alignment.Center
    ) {
        val context = LocalContext.current

        var productivityLevel by remember { mutableStateOf(1) }

        val outcome = when(productivityLevel) {
            1 -> R.drawable.accountability1
            2 -> R.drawable.accountability2
            else -> R.drawable.accountability3
        }
        if(productivityLevel > 3) {
            productivityLevel = 1
        }
        if (productivityLevel < 1) {
            productivityLevel = 3
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = outcome),
                contentDescription = null,
                modifier = Modifier
                    .size(550.dp)
                    .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),

                )

            Spacer(modifier = Modifier.height(16.dp))

            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {
                    productivityLevel -=1
                },
                    colors = ButtonDefaults.buttonColors(Green400),) {
                    Text(text = "Previous Note")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = {
                    productivityLevel +=1
                },
                    colors = ButtonDefaults.buttonColors(Green400),) {
                    Text(text = "Next Accountability Note")
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }

        }

    }


}


@Composable
@Preview

fun ProfileScreenPreview() {
    Mate()
}


