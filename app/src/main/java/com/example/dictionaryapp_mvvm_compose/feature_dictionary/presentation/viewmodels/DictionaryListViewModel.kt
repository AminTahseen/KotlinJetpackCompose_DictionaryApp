package com.example.dictionaryapp_mvvm_compose.feature_dictionary.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.data.data_source.remote.dto.DictionaryApiResponseItem
import com.example.dictionaryapp_mvvm_compose.feature_dictionary.domain.use_cases.RemoteDictionaryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryListViewModel  @Inject constructor(
    private val dictionaryUseCases: RemoteDictionaryUseCases,
): ViewModel() {

    private var _dictionaryRemote= MutableStateFlow(DictionaryApiResponseItem())
    private val dictionaryRemote: MutableStateFlow<DictionaryApiResponseItem> get() = _dictionaryRemote

    val allDictionaryRemoteItem : LiveData<DictionaryApiResponseItem> = dictionaryRemote.asLiveData()

    val errorMessage by lazy { MutableLiveData<String>() }

    val loading: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val isVisible =  mutableStateOf(false)

    fun getDictionaryDataFromRemote(word:String) {
        loading.value=false
        try {
            viewModelScope.launch {
                dictionaryUseCases.getRemoteDictionaryUseCases(wordValue = word)
                    .collect {
                        it.iterator().forEach { dictionaryItem->
                            _dictionaryRemote.value=dictionaryItem
                        }
                    }
                loading.value=true
            }
        }catch (e: Exception){
            loading.value=true
        }
    }

    fun validate(word: String):Boolean{
        return if(word.isEmpty()){
            errorMessage.value="Search Term Cannot be empty !"
            false
        }else{
            true
        }
    }

}