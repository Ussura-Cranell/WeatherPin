package com.ussura.application

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder


class MainActivity : ComponentActivity() {
    //@RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier){

    val scrollState = rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(0)
    }


    Scaffold(modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = modifier.fillMaxSize().padding(paddingValues)
                .background(Color.Yellow).verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

            content = {

                repeat(5) {
                    TextTesting()
                    Spacer(modifier.height(20.dp))
                    AnnotatedStringTesting()
                    Spacer(modifier.height(20.dp))
                    TextFieldTesting()
                    Spacer(modifier.height(20.dp))
                    ButtonTesting()
                    Spacer(modifier.height(20.dp))
                    ImageTest()
                    Spacer(modifier.height(20.dp))
                }
            }
        )
    }
}


@Composable
fun TextTesting(modifier: Modifier = Modifier) {

    Text(
        text = "Тестирование компонента текста",
        modifier = modifier,
        color = Color.Magenta,
        fontSize = 15.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Monospace,
        //fontWeight = 2.dp,
        letterSpacing = 2.sp,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.End,
        //lineHeight = 10.dp,
        overflow = TextOverflow.Clip,
        softWrap = false,
        maxLines = 3,
        style = TextStyle.Default
    )
}

@Composable
fun AnnotatedStringTesting(modifier: Modifier = Modifier) {
    Text(
        text = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color.Magenta)) {
                append("Невероятно! ")
            }
            append("Этот текст такой ")

            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color.Blue,
                textDecoration = TextDecoration.LineThrough, letterSpacing = 10.sp)) {
                append("разный...")
            }
        }
    )

    Text(
        modifier = Modifier.padding(15.dp),
        color = Color.DarkGray,
        text =
        buildAnnotatedString {
            append(
                "«Вот она, наступила решительная минута! Дошло до меня дело», " +
                "— подумал князь Андрей и, ударив лошадь, подъехал к Кутузову." +
                "— Надо остановить апшеронцев, — закричал он, — ваше высокопревосходительство!")

            withStyle(ParagraphStyle(lineHeight = 13.sp, textIndent = TextIndent(
                firstLine = 30.sp,
                restLine = 8.sp ),

            )){
                append(
                "Но в тот же миг все застлалось дымом, раздалась близкая стрельба, и наивно " +
                "испуганный голос в двух шагах от князя Андрея закричал: " +
                "«Ну, братцы, шабаш!» И как будто голос этот был команда. " +
                "По этому голосу все бросились бежать.")
            }
        }
    )
}

@Composable
fun ButtonTesting(modifier: Modifier = Modifier){
    //var color by remember { mutableStateOf(Color.Red) }
    // var enabled by remember { mutableStateOf(true) }
    var enabled by rememberSaveable { mutableStateOf(true) }

    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(/*containerColor = color*/),
        border = BorderStroke(2.dp, Color.Magenta),
        onClick = { /*color = Color.Red;*/ enabled = false }
    ) {
        Text("Зачеркнуть",
            textDecoration = if (enabled) TextDecoration.None else TextDecoration.LineThrough)
    }
}

@Composable
fun TextFieldTesting(modifier: Modifier = Modifier){

    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = password,
            onValueChange = { password = it; Log.d("somTest", it.toString()) },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Введите пароль") },
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Filled.Check
                        } else {
                            Icons.Filled.Face
                        },
                        contentDescription = if (isPasswordVisible) "Скрыть пароль" else "Показать пароль"
                    )
                }
            }
        )
    }
}

@Composable
fun ImageTest(modifier: Modifier = Modifier){

    val context = LocalContext.current
    val gifEnabledLoader = ImageLoader.Builder(context)
        .components {
            if ( SDK_INT >= 28 ) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    Row(
        modifier = modifier.fillMaxSize().padding(10.dp),//.background(Color.Cyan),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.cat),
                contentDescription = "just a cat",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Image(
            painter = rememberAsyncImagePainter(
                model = R.drawable.rei,
                imageLoader = gifEnabledLoader
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier.fillMaxHeight(1f)
                .aspectRatio(1f)
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp),
            )
        )
    }
}
