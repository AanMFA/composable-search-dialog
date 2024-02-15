package com.aanmfa.composablesearchdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aanmfa.composablesearchdialog.lib.SearchDialog
import com.aanmfa.composablesearchdialog.ui.theme.ComposableSearchDIalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableSearchDIalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var isDialogVisible by remember {
        mutableStateOf(false)
    }
    var chosenItem by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = chosenItem ?: "No item selected"
        )

        Button(onClick = { isDialogVisible = true }) {
            Text(text = "Show Search Dialog")
        }
    }

    if(isDialogVisible) {
        val items = (0 until 10).map{ "Item $it" }
        var shownItems by remember {
            mutableStateOf(items)
        }
        SearchDialog(
            modifier = Modifier.height(320.dp),
            shownItems = shownItems,
            onSearchValueChange = {
                shownItems = items.filter { item -> item.contains(it)}
            },
            onDismiss = { isDialogVisible = false },
            onItemSelected = {
                chosenItem = it
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposableSearchDIalogTheme {
        Greeting()
    }
}