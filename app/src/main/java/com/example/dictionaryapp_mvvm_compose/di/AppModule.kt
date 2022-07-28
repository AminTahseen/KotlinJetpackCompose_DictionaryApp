package com.example.dictionaryapp_mvvm_compose.di

import com.example.dictionaryapp_mvvm_compose.common.Constants
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.api.DictionaryApi
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.repository.DictionaryApiRepositoryImpl
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.repository.DictionaryApiRepository
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.use_cases.GetDictionaryWordUseCase
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.use_cases.RemoteDictionaryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDictionaryRetrofitInstance():DictionaryApi=
        Retrofit.Builder()
            .baseUrl(Constants.MAIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideDictionaryRemoteRepository(api:DictionaryApi):DictionaryApiRepository{
        return DictionaryApiRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideDictionaryRemotesUseCases(dictionaryApiRepo: DictionaryApiRepository):
            RemoteDictionaryUseCases {
        return RemoteDictionaryUseCases(
            getRemoteDictionaryUseCases = GetDictionaryWordUseCase(dictionaryApiRepo)
        )
    }
}