package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.remote.ServicePool
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import org.sopt.dosopttemplate.util.UiState
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<UiState<Boolean>>()
    val signUpResult: LiveData<UiState<Boolean>> get() = _signUpResult

    // 사용자가 입력하는 값들
    val inputId: MutableLiveData<String> = MutableLiveData()
    val inputPw: MutableLiveData<String> = MutableLiveData()
    val inputNickname: MutableLiveData<String> = MutableLiveData()

    // 사용자가 입력하는 값들의 유효성 검사, map으로 liveData 관찰하며 연쇄적으로 값이 바뀌도록 함
    val idFlag =
        inputId.map { it.isNotEmpty() && Pattern.compile(ID_PATTERN).matcher(it).find() }
    val pwFlag =
        inputPw.map { it.isNotEmpty() && Pattern.compile(PW_PATTERN).matcher(it).find() }
    val nicknameFlag = inputNickname.map { it.isNotEmpty() }

    // 버튼 활성화 관찰
    private val _signUpBtnFlag: MutableLiveData<Boolean> = MutableLiveData(false)
    val signUpBtnFlag: LiveData<Boolean> get() = _signUpBtnFlag

    fun signUpBtnFlag() {
        _signUpBtnFlag.value =
            idFlag.value == true && pwFlag.value == true && nicknameFlag.value == true
    }

    val dynamicTextSize: MutableLiveData<Int> = MutableLiveData(10)

    fun onUserTextSizeChanged(newTextSize: Int) {
        dynamicTextSize.value = newTextSize
    }

    fun signUpUserApi() = viewModelScope.launch {
        _signUpResult.value = UiState.Loading

        runCatching {
            ServicePool.authService.signUp(
                RequestSignupDto(
                    inputId.value ?: "",
                    inputPw.value ?: "",
                    inputNickname.value ?: "",
                ),
            )
        }.onSuccess {
        }.onFailure {
            _signUpResult.value = UiState.Failure(it.message.toString())
        }

        _signUpResult.value = UiState.Success(true)
    }

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
    }
}
