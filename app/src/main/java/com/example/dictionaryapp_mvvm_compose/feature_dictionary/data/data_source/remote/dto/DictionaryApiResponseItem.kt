package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto

data class DictionaryApiResponseItem(
    val license: License?=null,
    val meanings: List<Meaning>?=null,
    val phonetics: List<Phonetic>?=null,
    val sourceUrls: List<String>?=null,
    val word: String?=""
)