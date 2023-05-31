package com.android.acw.basic4

import android.os.Bundle
import androidx.compose.material3.Checkbox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.acw.basic4.ui.theme.Basic4Theme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SlotApiEx()
                }
            }
        }
    }
}

@Composable
fun CheckBoxEx() {
    /* Row(verticalAlignment = Alignment.CenterVertically){
         var checked=false
         Checkbox(checked=checked, onCheckedChange = {checked=!checked})
         *//*compose checkbox는 checked값이바껴야만 ui가 변경된다.
            그래서 첫번째 idea- onCheckedChange를 이용한다.-안된다. composition관련
            recompostion은 '상태'가 변경되어야만 발생한다. 그런데 단순 boolean변수는 compose에서
            관리하는 상태라고 할 수 없기 때문에 ui가 재구성되지 않는다.
         *//*
        Text(text="r u programmer?")
    }*/


    //val checked=remember{mutableStateOf(false)}
    /*
        remember를 하지않으면 compose가 화면을 다시그릴때, 즉 recompostion이 일어날때
        기존의 checked상태가 날아가버린다. 따라서 remember를 통해 기존상태를 기억해야한다.
     */
    /*Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(checked=checked.value,
            onCheckedChange = {checked.value=!checked.value})
        Text(text="r u programmer?")
    }*/
    //.value 이런식으로 접근하는게 불편할 수 있는데 checked를 by remember{~~}로 선언하면
    // value말고 그냥 checked 자체로 value를 참조할 수 있다. 이를 delegate properties라고한다.


    val (getter, setter) = remember { mutableStateOf(false) }
    //mutableState의 구현을 보면 value가 있고, getter와, setter가 있다.
    //따라서 val (a,b)=listOf(1,2)의 형태처럼 비구조화를 수행해서 getter와 setter를 받을 수 있다.

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = getter,
            onCheckedChange = setter
        )
        //그냥 onCheckedChange에 setter를 설정해도 상태가 정상적으로 변한다.
        //onCheckedChage를 눌러 구현을 보면, 내부적으로 설정을 클릭할 경우 기존 checked의 값을
        //참조하여 변경후 return한다 때문에 그값을 받아 set해주기만 하면 되기 때문에 setter를바로
        //전달해도 상태가 정상적으로 바뀌고 저장된다.
        Text(text = "r u programmer?", modifier = Modifier.clickable {
            setter(!getter)
            //text를 클릭해도 checkbox가 동작하도록 하였다.
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldEx() {
    var name by remember { mutableStateOf("Acw") }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it },
            label = {
                Row {
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "name")
                    Text("name")
                }
            })//이렇게 label을 넣어 textfield의 제목처럼 사용할 수 있다.

        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Hello $name!!")
    }//textfile에는 outlined와 그냥 textfield가 있는데 디자인에 맞게 사용하면된다. 사용법은 같음.
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarEx() {
    Column {
        TopAppBar(title = { Text("TopAppBar") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back button")
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.Settings, contentDescription = "setting")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "account")
                }
            })


        Text(text = "Hi TopAppBar")
    }
}






@Composable
fun CheckBoxWithSlot(checked: MutableState<Boolean>,
                     content:@Composable RowScope.() -> Unit){
    //이렇게 RowScope를 명시해주면 전달해주는 입장에서도 RowScope내의 속성들을 사용할 수가 있다.
    //어차피 전달되는 content가 Row내부에서 호출되기 때문에 전달될때 바로 RowScope내부의 modifier를
    //사용할 수 있으면 좋을 것이다.
    Row(verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.clickable {
        checked.value=!checked.value
    }){
        Checkbox(checked = checked.value, onCheckedChange ={
            checked.value=it
        } )

        content()
    }
    /*
    advanced: 여기서 text와 checkbox가 클릭했을 때 상태변경을 하기 위해 둘 다 코드작성을 할 수 있는데
    이 또한 밖으로 뺄 수 있다. 인자로 람다하나를 더 받아서 ex) onClicked : ()->Unit
    그것을 onCheckedChange와 clickable에 설정하면 된다.
     */
}




@Composable
fun SlotApiEx(){
    /*
    SlotApi란? - 람다를 받아 표시하는 건데 그냥 지금까지 써온 방식이다.
     */
    val checked1= remember{mutableStateOf(false)}
    val checked2= remember{mutableStateOf(false)}

    Column(horizontalAlignment= Alignment.CenterHorizontally) {
        CheckBoxWithSlot(checked = checked1) {
            Text(text="Text 1", modifier = Modifier.align(Alignment.CenterVertically))
            Text("text")
        }
        CheckBoxWithSlot(checked = checked2) {
            Text("Text 2")
            Text("text")
        }
    }//이렇게 모듈화를 시킬 수 있다.

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Basic4Theme {
        SlotApiEx()
    }
}