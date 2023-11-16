package org.sopt.dosopttemplate.presentation.main.peoplelist

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.remote.ServicePool.apiService
import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import retrofit2.Call
import retrofit2.Response

class PeopleListViewModel : ViewModel() {
    private val _peopleData = MutableLiveData<List<ResponsePeopleListDto>>()
    val peopleData: LiveData<List<ResponsePeopleListDto>> get() = _peopleData

    fun fetchPeopleData(context: Context) {
        apiService.PeopleListGet(2, 6)
            .enqueue(object : retrofit2.Callback<ResponsePeopleListDto> {
                override fun onResponse(
                    call: Call<ResponsePeopleListDto>,
                    response: Response<ResponsePeopleListDto>,
                ) {
                    if (response.isSuccessful) {
                        _peopleData.value = listOf(response.body() ?: return)
                    }
                }

                override fun onFailure(call: Call<ResponsePeopleListDto>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "ㅜ ㅜ 서버 에러 발생 ㅜ ㅜ",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            })
    }
}
