package org.sopt.dosopttemplate.presentation.main.peoplelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.remote.ServicePool.apiService
import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import org.sopt.dosopttemplate.util.UiState

class PeopleListViewModel : ViewModel() {
    private val _peopleData =
        MutableStateFlow<UiState<List<ResponsePeopleListDto>>>(UiState.Initial)
    val peopleData: StateFlow<UiState<List<ResponsePeopleListDto>>> get() = _peopleData

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
