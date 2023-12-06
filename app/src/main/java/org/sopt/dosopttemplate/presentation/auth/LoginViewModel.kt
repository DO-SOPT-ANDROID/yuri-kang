package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.remote.ServicePool
import org.sopt.dosopttemplate.data.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.remote.response.ResponseLoginDto
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.util.UiState
import retrofit2.Call

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<UiState<Boolean>>()
    val loginResult: LiveData<UiState<Boolean>> get() = _loginResult

    fun loginUser(inputId: String, inputPw: String) = viewModelScope.launch {
        _loginResult.value = UiState.Loading
        lateinit var getUserInfo: Call<ResponseLoginDto>

        runCatching {
            ServicePool.authService.login(
                RequestLoginDto(inputId, inputPw),
            )
        }.onSuccess {
            // getUserInfo = it
        }.onFailure {
            _loginResult.value = UiState.Failure(it.message.toString())
        }

        _loginResult.value = UiState.Success(true)
    }

    fun saveUserForAutoLogin(context: Context, signUpUser: User) {
        UserSharedPreferences.setUser(context, signUpUser)
    }
}
