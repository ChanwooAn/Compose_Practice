package com.android.acw.basic2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.acw.basic2.ui.theme.Basic2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic2Theme {
                ConstBoxEx()
            }
        }
    }
}

@Composable
fun BoxEx() {
    /*
    Box는 주로 2가지 용도로 많이 사용 하는 것 같음.
    1. Box자체를 만들기 위해
    2. 안드로이드 framework있는 frame layout같이 중첩시킬때
     */

    Box(
        modifier = Modifier.size(100.dp)
    ) {
        Text(text = "hello world", modifier = Modifier.align(Alignment.Center))
        Text(text = "hello world", modifier = Modifier.align(Alignment.BottomCenter))
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.Cyan)
                .align(Alignment.Center)
        )
    }

    Box() {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.Yellow)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Red)
        )
        //여기서 바깥 Box의 크기는 내부의item에 의해 결정된다. 첫번째 box가 70dp라 바깥 box의
        //크기는 70dp로 결정되고 두번째 box도 match parent라 70dp로 설정되어 첫번째box는 가려진다.
        //matchParentSize()말고, fillMaxSize를 쓰면 화면을 가득 채운다고 생각하면 된다.
    }
}

@Composable
fun RowEx() {

    Row(
        modifier = Modifier
            .height(40.dp)
            .width(200.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        //row는 가로로 배치되는거라, align은 설정상에서 vertical만 설정가능.
        // 즉 top bottom 등 세로에만 영향을 주는 요소만 사용가능.
        //Row상에서 align을 사용할 경우 Row에 들어있는 모든 item에 일단 설정된다.
        // 물론 각 item에서 modifier로 다시 align을 설정할경우 그 값이 반영된다.
        Text(
            text = "첫번째!!", modifier = Modifier
                .align(Alignment.Top)
                .weight(2f)
                .background(Color.Red)
        )
        Text(
            text = "두번째!!", modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .background(Color.Blue)
        )
        Text(
            text = "세번째!!", modifier = Modifier
                .weight(1f)
                .background(Color.Cyan)
        )
        Icon(
            imageVector = Icons.Filled.AccountBox, contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .background(Color.Yellow)
        )
    }
}

@Composable
fun ColumnEx() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "첫번째!!", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(2f)
                    .background(Color.Red)
            )
            Text(
                text = "두번째!!", modifier = Modifier
                    .weight(1f)
                    .background(Color.Blue)
            )
            Text(
                text = "세번째!!", modifier = Modifier
                    .weight(1f)
                    .background(Color.Cyan)
                    .align(Alignment.Start)
            )
            Icon(
                imageVector = Icons.Filled.AccountBox, contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Yellow)
            )
        }
    }
}

@Composable
fun ConstBoxEx() {
    Surface() {
        Basic2Theme {
            Outer()
        }
    }
}

@Composable
fun Outer() {
    Column() {
        Inner(
            Modifier
                .widthIn(max=350.dp)
                .heightIn(min=100.dp,max=151.dp)
        )
    }
}

@Composable
fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        if(maxHeight>150.dp){
            Log.d("maxHeight",maxHeight.toString())
            Text(text="this is quite long!",modifier=Modifier.align(Alignment.BottomCenter))
        }
        Text(text = "maxW:$maxWidth maxH:$maxHeight minW:$minWidth minH:$minHeight")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Basic2Theme {
        ConstBoxEx()
    }
}