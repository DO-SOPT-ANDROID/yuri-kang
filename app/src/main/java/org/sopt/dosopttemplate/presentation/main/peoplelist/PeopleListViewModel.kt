package org.sopt.dosopttemplate.presentation.main.peoplelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.remote.ServicePool.apiService
import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import org.sopt.dosopttemplate.util.UiState

class PeopleListViewModel : ViewModel() {
    private val _peopleData = MutableLiveData<UiState<List<ResponsePeopleListDto>>>()
    val peopleData: LiveData<UiState<List<ResponsePeopleListDto>>> get() = _peopleData

    fun fetchPeopleData() = viewModelScope.launch {
        _peopleData.value = UiState.Loading
        lateinit var peopleList: List<ResponsePeopleListDto>

        runCatching {
            apiService.PeopleListGet(2, 6)
        }.onSuccess {
            peopleList = listOf<ResponsePeopleListDto>(it)
            _peopleData.value = UiState.Success(peopleList)
        }.onFailure {
            _peopleData.value = UiState.Failure(it.message.toString())
        }
    }
}
