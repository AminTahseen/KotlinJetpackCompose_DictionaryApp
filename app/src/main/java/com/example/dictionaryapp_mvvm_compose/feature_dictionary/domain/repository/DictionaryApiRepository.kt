package com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.repository

import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.DictionaryApiResponse

interface DictionaryApiRepository {

    suspend fun getDictionaryWordDetails(word:String):DictionaryApiResponse
}