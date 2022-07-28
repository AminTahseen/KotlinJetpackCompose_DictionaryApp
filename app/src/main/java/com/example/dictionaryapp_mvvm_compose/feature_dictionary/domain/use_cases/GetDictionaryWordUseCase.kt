package com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.use_cases

import android.util.Log
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.DictionaryApiResponse
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.repository.DictionaryApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetDictionaryWordUseCase(
    private val dictionaryApiRepository: DictionaryApiRepository,
) {
    suspend operator fun invoke(wordValue: String): Flow<DictionaryApiResponse> = flow {
        try {
            val data = dictionaryApiRepository.getDictionaryWordDetails(word =wordValue)
            emit(value = data)
        }catch (e: HttpException){
            Log.d("exceptionHTTP",e.message())
        }
    }
}