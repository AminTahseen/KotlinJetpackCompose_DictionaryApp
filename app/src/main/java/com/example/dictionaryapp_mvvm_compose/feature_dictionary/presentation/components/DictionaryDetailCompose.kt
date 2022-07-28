package com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.components

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionaryapp_mvvm_compose.R
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.Meaning
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.Phonetic
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.viewmodels.DictionaryListViewModel
import com.example.dictionaryapp_mvvm_compose.ui.theme.DictionaryApp_MVVM_ComposeTheme

@Composable
fun DictionaryDetail(viewModel: DictionaryListViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
            .fillMaxSize()
    ){
        if(viewModel.loading.observeAsState().value==true) {
            Text(
                text = viewModel.allDictionaryRemoteItem.observeAsState().value?.word.toString(),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            viewModel.allDictionaryRemoteItem.observeAsState().value?.phonetics?.let {
                PhoneticsSection(
                    phonetics = it
                )
            }
            viewModel.allDictionaryRemoteItem.observeAsState().value?.meanings?.let {
                DefinitionSection(
                    meaning = it
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                Column(modifier = Modifier
                    .padding(top = 20.dp)
                ) {
                    Text(
                        text = "Synonyms".uppercase(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Column(modifier = Modifier
                    .padding(top = 20.dp)
                ) {
                    Text(
                        text = "Antonyms".uppercase(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            viewModel.allDictionaryRemoteItem.observeAsState()
                .value?.meanings?.iterator()?.forEach {
                    if((it.synonyms.isNotEmpty() && it.synonyms.size>=5) && it.antonyms.isEmpty()){
                        MeaningSection(
                            it.synonyms.subList(0,5),
                            it.antonyms
                        )
                    }else if(it.synonyms.isEmpty() && (it.antonyms.isNotEmpty() && it.antonyms.size>=5)) {
                        MeaningSection(
                            it.synonyms,
                            it.antonyms.subList(0, 5)
                        )
                    }
                    else if((it.synonyms.isNotEmpty() && it.synonyms.size>=5)&& (it.antonyms.isNotEmpty() && it.antonyms.size>=5)) {
                        MeaningSection(
                            it.synonyms.subList(0,5),
                            it.antonyms.subList(0, 5)
                        )
                    }
                    else{
                        MeaningSection(
                            it.synonyms,
                            it.antonyms
                        )
                    }
                }

        }else{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable 
fun PhoneticsSection(phonetics:List<Phonetic>){
    val ctx = LocalContext.current
    val mediaPlayer = MediaPlayer()
    phonetics.iterator().forEach { phonetic ->
        Row(Modifier.fillMaxWidth()) {
           if(phonetic.text!=null){
                Text(
               modifier = Modifier.padding(end = 10.dp),
               text = phonetic.text,
               fontSize = 25.sp,
               color = Color.Blue
               )
               IconButton(modifier = Modifier.
               padding(top=5.dp)
                       then(Modifier.size(25.dp)),
                   onClick = {
                      if(phonetic.audio!=null){
                          try {
                              mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                              mediaPlayer.setDataSource(phonetic.audio)
                              mediaPlayer.prepare()
                              mediaPlayer.start()
                          }catch (e:Exception){
                              Log.d("exceptionAudio",e.message.toString());
                          }

                      }else{
                          Toast.makeText(ctx, "No Audio Found", Toast.LENGTH_SHORT).show()
                      }
                   }) {
                   Icon(

                       painter = painterResource(id = R.drawable.ic_baseline_volume_up_24),
                       "contentDescription",
                       tint = Color.Blue
                   )
               }
           }
        }
    }
}
@Composable
fun DefinitionSection(meaning:List<Meaning>){
    Column(modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth()) {
        Text(
            text = "Definitions".uppercase(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        meaning.iterator().forEach { meaningObj ->
            meaningObj.definitions.forEach { definition ->
                Text(
                    text =definition.definition,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
        }
    }
}
@Composable
fun MeaningSection(syno:List<String>,anto:List<String>){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(modifier = Modifier
            .fillMaxWidth().weight(1f)
        ) {
            if(syno.isNotEmpty()){
                syno.iterator().forEach {
                    Text(text = it)
                }
            }else{
                Text(text = "*Not Available*",color=Color.Gray)
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth().weight(1f),
        ) {
            if(anto.isNotEmpty()) {
                anto.iterator().forEach {
                    Text(text = it)
                }
            }else{
                Text(text = "*Not Available*",color=Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryApp_MVVM_ComposeTheme {
       // DictionaryDetail()
    }
}