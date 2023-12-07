package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.remote.ServicePool
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import org.sopt.dosopttemplate.util.UiState
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableStateFlow<UiState<Boolean>>(UiState.Initial)
    val signUpResult: StateFlow<UiState<Boolean>> get() = _signUpResult.asStateFlow()

    // 사용자가 입력하는 값들
    val inputId: MutableStateFlow<String> = MutableStateFlow("")
    val inputPw: MutableStateFlow<String> = MutableStateFlow("")
    val inputNickname: MutableStateFlow<String> = MutableStateFlow("")

    // 사용자가 입력하는 값들의 유효성 검사, map으로 liveData 관찰하며 연쇄적으로 값이 바뀌도록 함
    val idFlag =
        inputId.map { it.isNotEmpty() && ID_REGEX.matcher(it).find() }
    val pwFlag =
        inputPw.map { it.isNotEmpty() && PW_REGEX.matcher(it).find() }
    val nicknameFlag = inputNickname.map { it.isNotEmpty() }

    // 버튼 활성화 관찰
    private val _signUpBtnFlag: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val signUpBtnFlag: StateFlow<Boolean> get() = _signUpBtnFlag.asStateFlow()

    fun signUpBtnFlag() {
        viewModelScope.launch {
            combine(idFlag, pwFlag, nicknameFlag) { id, pw, nickname ->
                id && pw && nickname
            }.collect { result ->
                _signUpBtnFlag.value = result
            }
        }
    }

    val dynamicTextSize: MutableStateFlow<Int> = MutableStateFlow(10)

    fun onUserTextSizeChanged(newTextSize: Int) {
        dynamicTextSize.value = newTextSize
    }

    fun signUpUserApi() = viewModelScope.launch {
        _signUpResult.emit(UiState.Loading) // emit: 결과를 플로우에 방출
        delay(refreshMs) // 네트워크 요청이 완료될 때까지 중단

        runCatching {
            ServicePool.authService.signUp(
                RequestSignupDto(
                    inputId.value,
                    inputPw.value,
                    inputNickname.value,
                ),
            )
            // 여기서 result를 어떻게 처리할지 추가 작업이 필요할 것입니다.
        }.onFailure {
            _signUpResult.emit(UiState.Failure(it.message.toString()))
        }

        _signUpResult.emit(UiState.Success(true))
    }

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        val ID_REGEX: Pattern = Pattern.compile(ID_PATTERN)
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
        val PW_REGEX: Pattern = Pattern.compile(PW_PATTERN)
        val refreshMs = 2000L
    }
}
