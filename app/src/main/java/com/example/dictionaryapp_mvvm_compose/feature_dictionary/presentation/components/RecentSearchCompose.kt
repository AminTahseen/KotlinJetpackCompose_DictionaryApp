package com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.components


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.dictionaryapp_mvvm_compose.MainActivity

@Composable
fun RecentSearchResult(word: String,context:Context) {
    ClickableText(
        modifier = Modifier
            .padding(10.dp),
        text = AnnotatedString(word),
        onClick = {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("word",word)
            context.startActivity(intent)
        }
    )
}
