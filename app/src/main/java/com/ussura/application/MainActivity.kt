package com.ussura.application

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ussura.application.ui.theme.WeatherPinTheme
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold (
                modifier = Modifier.fillMaxSize(),
                content = { paddingValues ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        content = {
                            Text(text = "WeatherPin",
                                fontSize = 28.sp)
                            val context = LocalContext.current
                            Button({
                                Toast.makeText(context, "Push!", Toast.LENGTH_SHORT).show();
                                   Log.d("push", "pushed: \"Push me!\"")},
                                content = {
                                    Text("Push me!",
                                        fontSize = 24.sp)
                                })
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun HeaderApplication(paddingValues: PaddingValues){

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center,
        content = {
            Text(text = "WeatherPin",
                fontSize = 28.sp)
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun HeaderApplication(){
    HeaderApplication(PaddingValues())
}




