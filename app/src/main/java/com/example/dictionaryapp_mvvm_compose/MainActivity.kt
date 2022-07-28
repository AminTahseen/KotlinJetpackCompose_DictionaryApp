package com.example.dictionaryapp_mvvm_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.components.DictionaryDetail
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.viewmodels.DictionaryListViewModel
import com.example.dictionaryapp_mvvm_compose.ui.theme.DictionaryApp_MVVM_ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryApp_MVVM_ComposeTheme {
                var dictionaryListViewModel = ViewModelProvider(this@MainActivity)[DictionaryListViewModel::class.java]
                var word=intent.extras?.get("word").toString()
                BuildMainActivity(word = word, dictionaryListViewModel =dictionaryListViewModel)
            }
        }
    }
}

@Composable
fun BuildMainActivity(word:String,dictionaryListViewModel: DictionaryListViewModel){
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                dictionaryListViewModel.getDictionaryDataFromRemote(word)
                DictionaryDetail(viewModel = dictionaryListViewModel)
            }
        }
    )
}

