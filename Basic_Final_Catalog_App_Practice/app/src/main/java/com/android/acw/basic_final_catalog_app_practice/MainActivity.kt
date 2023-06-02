package com.android.acw.basic_final_catalog_app_practice

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.acw.basic_final_catalog_app_practice.ui.theme.Basic_Final_Catalog_App_PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic_Final_Catalog_App_PracticeTheme {
                // A surface container using the 'background' color from the theme

                CatalogEx(itemList)
            }
        }
    }
}
@Composable
fun Item(itemData:ItemData){
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier=Modifier.padding(16.dp)
    ){
        Column(modifier = Modifier.padding(8.dp)){
            Image(
                painter= painterResource(id = itemData.imageId),
                contentDescription = "qwdq"
            )
        }
        Spacer(modifier=Modifier.size(8.dp))
        Text(text=itemData.title,
            style=MaterialTheme.typography.headlineLarge
            )
        Text(
            text=itemData.description
        )
    }

}
@Composable
fun CatalogEx(itemList:List<ItemData>){
    LazyColumn{
        //recycler view라고 생각하면 된다.
        items(itemList){
            //각항목에대한 scope
            item->
            Item(item)
        }
        /*
            수동으로 넣는법.
            item{
                Item(itemList[0])
            }
            이런식으로 하나씩 넣는 방법도 있다.
         */

    }
}




data class ItemData(
    val imageId: Int,
    val title:String,
    val description:String
)

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Basic_Final_Catalog_App_PracticeTheme {
        Item(
            ItemData(
                imageId = R.drawable.wall,
                title="벽",
                description="벽그림에 대한 설명 sth sth sth sth qdwjqwjdiqjwdoiqjwdojqwdoijqdwoijqwdjoiqojdwoiqjdwoiwqjdoiqjdoiwqjoiqdoiwqjdoiqwjdodwqjqjdoijwqdoijqoidjqoiwjdiqdwj"
            )
        )
    }
}

val itemList=listOf(
    ItemData(
        imageId = R.drawable.wall,
        title="벽",
        description="벽그림에 대한 설명 sth sth sth sth qdwjqwjdiqjwdoiqjwdojqwdoijqdwoijqwdjoiqojdwoiqjdwoiwqjdoiqjdoiwqjoiqdoiwqjdoiqwjdodwqjqjdoijwqdoijqoidjqoiwjdiqdwj"
    ),
    ItemData(
        imageId = R.drawable.wall,
        title="벽",
        description="벽그림에 대한 설명 sth sth sth sth qdwjqwjdiqjwdoiqjwdojqwdoijqdwoijqwdjoiqojdwoiqjdwoiwqjdoiqjdoiwqjoiqdoiwqjdoiqwjdodwqjqjdoijwqdoijqoidjqoiwjdiqdwj"
    ),
    ItemData(
        imageId = R.drawable.wall,
        title="벽",
        description="벽그림에 대한 설명 sth sth sth sth qdwjqwjdiqjwdoiqjwdojqwdoijqdwoijqwdjoiqojdwoiqjdwoiwqjdoiqjdoiwqjoiqdoiwqjdoiqwjdodwqjqjdoijwqdoijqoidjqoiwjdiqdwj"
    ),
    ItemData(
        imageId = R.drawable.wall,
        title="벽",
        description="벽그림에 대한 설명 sth sth sth sth qdwjqwjdiqjwdoiqjwdojqwdoijqdwoijqwdjoiqojdwoiqjdwoiwqjdoiqjdoiwqjoiqdoiwqjdoiqwjdodwqjqjdoijwqdoijqoidjqoiwjdiqdwj"
    )
)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Basic_Final_Catalog_App_PracticeTheme {
        CatalogEx(itemList)
    }
}
