package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto

data class Phonetic(
    val audio: String,
    val license: LicenseX,
    val sourceUrl: String,
    val text: String
)