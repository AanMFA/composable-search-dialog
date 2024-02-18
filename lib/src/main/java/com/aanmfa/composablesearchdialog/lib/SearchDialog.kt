package com.aanmfa.composablesearchdialog.lib

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.window.Dialog

@Composable
fun SearchDialog(
    modifier: Modifier = Modifier,
    shownItems: List<String> = emptyList(),
    noItemFoundText: String = "No item found",
    onSearchValueChange: (String) -> Unit,
    onItemSelected: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onSearchValueChange(searchQuery)
                },
                Modifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(shownItems.isEmpty())
                    item {
                        Text(text = noItemFoundText)
                    }
                items(count = shownItems.size) { i ->
                    if(i != 0)
                        Divider()
                    StringItem(item = shownItems[i]) {
                        onItemSelected(shownItems[i])
                        onDismiss()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun NoItemPreview() {
    PreviewComposable(emptyList())
}

@Composable
@Preview
private fun ItemsFoundPreview() {
    PreviewComposable(
        listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")
    )
}

@Composable
private fun PreviewComposable(items: List<String>) {
    var shownItems by remember {
        mutableStateOf(items)
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
        }
        SearchDialog(
            shownItems = shownItems,
            onSearchValueChange = {
                shownItems = items.filter { item -> item.contains(it)}
            },
            onItemSelected = {},
            onDismiss = {},
        )
    }
}