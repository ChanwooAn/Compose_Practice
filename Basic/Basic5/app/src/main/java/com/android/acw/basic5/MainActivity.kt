package com.android.acw.basic5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.acw.basic5.ui.theme.Basic5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Basic5Theme {
                // A surface container using the 'background' color from the theme

                ScaffoldEx()

            }
        }
    }
}


@Composable
fun CheckboxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { toggleState() }
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { toggleState() }
        )
        content()
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldEx() {
    var checked by remember {
        mutableStateOf(false)
    }
    /*
    Scaffold는 그냥 layout의 일종이라고 생각하면 되는데, 기본 속성으로 topbar, bottom bar등을
    지원해서 쉽게 material design이 지향하는 대로 layout을 구성할 수 있다.
     */
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                },
                title = {
                    Text(text = "Scaffold App")
                },
            )
        },
        content = {innerPadding->
            //content에 기본적으로 주는 innerPadding을 꼭 설정하자.
            //그렇지 않으면 content와 topbar가 겹쳐보일것이다!!!...
            Column(modifier=Modifier.padding(innerPadding)) {
                CheckboxWithContent(checked = checked,
                    toggleState = { checked = !checked }) {
                    Text(text = "Compose is beautiful")
                }
                CheckboxWithContent(checked = checked,
                    toggleState = { checked = !checked }) {
                    Text(text = "Compose is beautiful")
                }
                CheckboxWithContent(checked = checked,
                    toggleState = { checked = !checked }) {
                    Text(text = "Compose is beautiful")
                }
                CheckboxWithContent(checked = checked,
                    toggleState = { checked = !checked }) {
                    Text(text = "Compose is beautiful")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Basic5Theme {
        ScaffoldEx()
    }
}