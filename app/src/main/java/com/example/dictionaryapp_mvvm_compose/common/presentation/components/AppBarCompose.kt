package com.example.dictionaryapp_mvvm_compose.common.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dictionaryapp_mvvm_compose.ui.theme.DictionaryApp_MVVM_ComposeTheme

@Composable
fun AppBar(name:String) {
    TopAppBar(
        title = {
            Text(text = name)
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryApp_MVVM_ComposeTheme {
        AppBar(name = "DictionaryApp")
    }
}
