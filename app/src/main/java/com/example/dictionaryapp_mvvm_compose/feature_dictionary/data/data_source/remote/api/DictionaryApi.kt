package com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.api

import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.DictionaryApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("{word}")
    suspend fun getNotesFromRemote(@Path("word") word:String): DictionaryApiResponse

}