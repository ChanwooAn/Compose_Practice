package com.android.acw.basic3

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.android.acw.basic3.ui.theme.Basic3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic3Theme {
                Surface(
                    modifier = Modifier.background(Color.White)
                ) {
                    Column {
                        ProfileCardEx()
                        ProfileCardEx()
                    }
                    // A surface container using the 'background' color from the theme
                }
            }
        }
    }

    @Composable
    fun ImageEx() {
        Column {
            Image(
                painter = painterResource(id = R.drawable.wall),
                contentDescription = "canon"
            )

            Image(
                imageVector = Icons.Filled.Settings,
                contentDescription = "setting"
            )
            //Image에는 bitmap= 의 식으로 bitmap설정도 되긴하지만 bitmap을 가져오는것은
            // context를 필요로 하는 작업이다. ui단에서 바로 처리할 수는 없다.

        }
    }

    @Composable
    fun NetworkImageEx() {
        val painter =
            rememberImagePainter(data = "https://src.hidoc.co.kr/image/lib/2022/5/12/1652337370806_0.jpg")
        //remember- composable할때 기억해놨다가 다시 사용한다는 뜻.
        /*Image(
            painter=painter,
            contentDescription = "고양이"
        )*/
        Column {
            AsyncImage(
                model = "https://src.hidoc.co.kr/image/lib/2022/5/12/1652337370806_0.jpg",
                contentDescription = "cat"
            )
            AsyncImage(
                model = "https://src.hidoc.co.kr/image/lib/2022/5/12/1652337370806_0.jpg",
                contentDescription = "cat"
            )
        }
        /*
        rememberImagePainter나 AsyncImage모두 coil library를 사용하는 것이기 때문에 coil 의존성을 꼭 추가시켜주도록하자.
        coil 공식문서에서는 Async방식을 추천하고 있다.
         */

    }


    data class CardData(
        val imageUri: String,
        val imageDescription: String,
        val author: String,
        val description: String
    )

    @Composable
    fun ProfileCardEx() {
        val cardData = CardData(
            imageUri = "https://src.hidoc.co.kr/image/lib/2022/5/12/1652337370806_0.jpg",
            imageDescription = "cat",
            author = "acw",
            description = "cat is cute"
        )

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                ,
            colors = CardDefaults.cardColors(Color.White)
        //이미지의 background색을 변경하기 위해선 colors를 이용해야함
        //modifier의 background는 적용되지 않음.
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                AsyncImage(
                    model = cardData.imageUri,
                    contentDescription = cardData.imageDescription,
                    placeholder = ColorPainter(Color.Gray),//이미지가없을때 기본이미지
                    contentScale = ContentScale.Crop,//비율안맞을때 설정하기
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)//원으로 crop해서 넣기
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(text = cardData.author)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = cardData.description)
                }
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Basic3Theme {
            ProfileCardEx()
        }
    }
}