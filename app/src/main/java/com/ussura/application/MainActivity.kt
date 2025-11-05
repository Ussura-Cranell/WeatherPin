package com.ussura.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(Modifier.fillMaxSize()) { paddingValues: PaddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            LettersColumn(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .align(Alignment.CenterStart)
            )
            NumbersColumn(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .align(Alignment.CenterEnd)
            )

            Column(
                modifier = Modifier.align(Alignment.Center).offset((-13).dp)
                    .fillMaxWidth(0.4f).fillMaxHeight().background(Color.Yellow),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                repeat(5){
                    CircularButton()
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
}

@Composable
fun LettersColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(0x00FFFFFF))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(26) { index ->
            LetterButton(letter = ('A' + index))
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}

@Composable
fun NumbersColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(0x00FFFFFF))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        repeat(26) { index ->
            NumberButton(number = index)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}

@Composable
fun LetterButton(letter: Char) {
    var enabled by remember { mutableStateOf(true) }
    var color by remember { mutableStateOf(Color.Green) }
    Button(
        modifier = Modifier
            .fillMaxHeight().background(color)
            .fillMaxWidth(0.75f)
            .border(BorderStroke(2.dp, Color.Cyan), shape = CircleShape),
        onClick = {
            enabled = false;
            color = Color.Red},
        shape = CircleShape,
        enabled = enabled
    ) {
        Text(
            text = letter.toString(),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}

@Composable
fun NumberButton(number: Int) {

    var enabled by remember { mutableStateOf(true) }
    var color by remember { mutableStateOf(Color.Green) }

    Button(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f)
            .border(BorderStroke(2.dp, Color.Cyan), shape = CircleShape),
        onClick = {
            enabled = false;
            color = Color.Red
        },
        shape = CircleShape,
        enabled = enabled

    ) {
        Text(
            text = "%02d".format(number),
            modifier = Modifier
                .fillMaxSize().background(color)
                .wrapContentSize()
        )
    }
}

@Composable
fun CircularButton(modifier: Modifier = Modifier) {
    //val context = LocalContext.current
    var color: Long by remember { mutableLongStateOf(Random.nextLong(0, 0xFF_FF_FF) or 0xFF_00_00_00) }
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color(color))
            .clickable {
                // Toast.makeText(context, "Push!", Toast.LENGTH_SHORT).show();
                color = changeColor(color)
                },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Очень длинный текст который будет обрезан, но так как длины не хватило, придется дописать ещё что-нибудь",
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

fun changeColor(num: Long = 0xFF_00_00_00):Long {
    val step = 0x6EB3E
    var colorWithoutAlpha = num and 0x00_FF_FF_FF
    colorWithoutAlpha = if (colorWithoutAlpha + step > 0xFF_FF_FF) 0xFF_00_00_00
    else colorWithoutAlpha + step or 0xFF_00_00_00
    //Log.d("color_test", colorWithoutAlpha.toString())
    return colorWithoutAlpha

}