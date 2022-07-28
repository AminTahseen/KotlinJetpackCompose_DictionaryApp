package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto

data class Meaning(
    val antonyms: List<String>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)