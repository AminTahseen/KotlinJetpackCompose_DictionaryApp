package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
)