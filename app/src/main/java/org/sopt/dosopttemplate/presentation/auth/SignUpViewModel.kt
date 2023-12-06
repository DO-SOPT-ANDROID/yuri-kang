package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.remote.ServicePool
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

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

//    val idFlag: LiveData<Boolean> get() = _idFlag
//    val pwFlag: LiveData<Boolean> get() = _pwFlag
//    val nicknameFlag: LiveData<Boolean> get() = _nicknameFlag

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

    fun signUpUserApi(context: Context) {
        ServicePool.authService.signUp(
            RequestSignupDto(
                inputId.value!!,
                inputPw.value!!,
                inputNickname.value!!,
            ),
        )
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>,
                ) {
                    if (response.isSuccessful) {
                        _signUpResult.value = true

                        User(
                            inputId.value!!,
                            inputPw.value!!,
                            inputNickname.value!!,
                        )
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "ㅜ ㅜ 서버 에러 발생 ㅜ ㅜ",
                        Toast.LENGTH_SHORT,
                    ).show()
                    _signUpResult.value = false
                }
            })
    }

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
    }
}
