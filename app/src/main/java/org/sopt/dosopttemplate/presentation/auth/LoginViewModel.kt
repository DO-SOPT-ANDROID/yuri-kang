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
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.util.UiState

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<UiState<Boolean>>()
    val loginResult: LiveData<UiState<Boolean>> get() = _loginResult

    private val _getLoginInfo = MutableLiveData<UiState<User>>()
    val getLoginInfo: LiveData<UiState<User>> get() = _getLoginInfo

    fun loginUser(inputId: String, inputPw: String) = viewModelScope.launch {
        _loginResult.value = UiState.Loading

        runCatching {
            ServicePool.authService.login(
                RequestLoginDto(inputId, inputPw),
            )
        }.onSuccess {
            // getUserInfo = it
            _loginResult.value = UiState.Success(true)
            _getLoginInfo.value = UiState.Success(User(it.id.toString(), it.username, it.nickname))
        }.onFailure {
            _loginResult.value = UiState.Failure(it.message.toString())
        }
    }

    fun saveUserForAutoLogin(context: Context, signUpUser: User) {
        UserSharedPreferences.setUser(context, signUpUser)
    }
}
