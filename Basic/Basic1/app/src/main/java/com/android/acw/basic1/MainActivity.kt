package com.android.acw.basic1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.acw.basic1.ui.theme.Basic1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .width(200.dp)
                    ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    SurfaceExample()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!\nHello $name!\nHello $name!",
        color= Color(0xffff9944),//Color.Red 등 compose에서 기본제공하는 색이 많음
        fontSize=30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        letterSpacing = 2.sp,
        maxLines = 2,//최대 볼 line수. 그렇다고 완전히 가려지는것은 아니고, 3번째 줄이 살짝 보일 수도 있다.
        // 완전히 가리고 싶다면 overflow속성을 사용해야함.
        overflow = TextOverflow.Ellipsis, // 또는 TextOverflow.Clip
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center


    )


}

@Composable
fun ButtonGreeting(onButtonClicked:()->Unit){
    //button 안에 여러개의 view를 배치할 수 있음!!
    //()안에는 buttton 의 속성을 선언하고
    //{}안에 view들을 배치하기
    Button(
        onClick = onButtonClicked,
        colors=ButtonDefaults.buttonColors(
            containerColor =Color.Black,
            contentColor = Color.Green
        ),//button의 background를 변경하고싶으면 modifier에 background속성이
        //있긴하지만 안먹힌다. colors를 통해 설정할수 있다.
        enabled = false,
        //enabled를 false로 설정하면 click이 안되지만 내부에 배치한 view들을
        //따로 clickable을 설정하여 클릭하도록 할 수 있다.
        border = BorderStroke(10.dp,Color.Magenta),//외곽부분 지정하기
        shape = RoundedCornerShape(10.dp),
        contentPadding= PaddingValues(20.dp)
        ){
        Icon(
            modifier=Modifier.background(Color.Blue),
            imageVector = Icons.Filled.Send,
            contentDescription = null//어떤 아이콘인지 설명. 필요없으면 null

        )
        Spacer(modifier= Modifier
            .size(ButtonDefaults.IconSpacing)
            .background(Color.Red))
        //기본값으로 set하긴했는데 그냥 dp로 set해도 됨.
        Text(text="button",
        modifier= Modifier
            .clickable { }
            .offset(x = 10.dp)
            .background(Color.Yellow)
        //이러면 text만 클릭가능한데, 그렇다고 button에 set한 listener가 작동하진 않는다.
        )
    }
}

@Composable
fun SurfaceExample(){
    Surface(
        border=BorderStroke(width=2.dp,color=Color.Magenta),
        modifier = Modifier.padding(5.dp),
        shadowElevation = 5.dp,
        tonalElevation = 5.dp,
        shape = CircleShape,
    color=MaterialTheme.colorScheme.secondary,
    //error값이 오는게 아니라, error에 맞춰서 onError값이 자동으로 set됨
    ) {

        Text(text="surface test",
        modifier=Modifier.padding(5.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Basic1Theme {
        SurfaceExample()
    }
}