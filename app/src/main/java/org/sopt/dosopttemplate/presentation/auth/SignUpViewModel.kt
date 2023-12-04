package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.remote.ServicePool
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import retrofit2.Call
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    private val _isValidId = MutableLiveData<Boolean>()
    val isValidId: LiveData<Boolean> get() = _isValidId

    private val _isValidPw = MutableLiveData<Boolean>()
    val isValidPw: LiveData<Boolean> get() = _isValidPw

    private val _isValidNickname = MutableLiveData<Boolean>()
    val isValidNickname: LiveData<Boolean> get() = _isValidNickname

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
    }


    fun checkValid(signUpUser: User) {
        if (signUpUser.userId.isEmpty() || signUpUser.userId.isBlank()) {
            _isValidId.value = true
        } else {
            val idPattern = signUpUser.userId.matches(Regex(ID_PATTERN))
            _isValidId.value = idPattern
        }

        val pwPattern = signUpUser.userPw.matches(Regex(PW_PATTERN))
        _isValidPw.value = pwPattern

        val nicknamePattern = signUpUser.userNickname.isNotBlank() &&
            signUpUser.userNickname.isNotEmpty()
        _isValidNickname.value = nicknamePattern
    }

    fun signUpUserApi(signUpUser: User, context: Context) {
        ServicePool.authService.signUp(
            RequestSignupDto(
                signUpUser.userId,
                signUpUser.userPw,
                signUpUser.userNickname,
            ),
        )
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>,
                ) {
                    if (response.isSuccessful) {
                        _signUpResult.value = true
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
}
