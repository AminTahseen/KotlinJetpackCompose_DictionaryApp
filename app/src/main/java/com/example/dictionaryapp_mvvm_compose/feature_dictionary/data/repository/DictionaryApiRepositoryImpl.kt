package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.repository

import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.api.DictionaryApi
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.DictionaryApiResponse
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.repository.DictionaryApiRepository
import javax.inject.Inject

class DictionaryApiRepositoryImpl@Inject constructor(private val dictionaryApi:DictionaryApi)
    :DictionaryApiRepository {

    override suspend fun getDictionaryWordDetails(word: String): DictionaryApiResponse {
        return dictionaryApi.getNotesFromRemote(word = word)
    }
}