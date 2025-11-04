package com.ussura.application

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // check INTERNET manifest
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 0)
        }

        // init OSMDroid
        val ctx = applicationContext

        Configuration.getInstance().load(ctx, androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx))
        Configuration.getInstance().userAgentValue = packageName

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold (
                modifier = Modifier.fillMaxSize().background(Color.Yellow),
                content = { paddingValues ->

                    OsmdroidMapView(paddingValues)
                    HeaderApplication(paddingValues)
                }
            )
        }
    }
}

@Composable
fun HeaderApplication(paddingValues: PaddingValues){

    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                }
            )
        }
    )
}

@Composable
fun OsmdroidMapView(paddingValues: PaddingValues) {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(10.0)
            }
        }
    )
}