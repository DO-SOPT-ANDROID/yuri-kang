package org.sopt.dosopttemplate.presentation.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.data.remote.ServicePool.authService
import org.sopt.dosopttemplate.data.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.remote.response.ResponseLoginDto
import org.sopt.dosopttemplate.di.UserSharedPreferences
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>() // 외부에 공개하지 않는, 비즈니스 로직에서는 private 변수를 사용한다.
    val loginResult: LiveData<Boolean> get() = _loginResult // 내부에서 쓰는 변수에 할당, 내부 상태를 변경 가능, get 메서드가 라이브데이터 타입 반환

    val isLoginButtonClicked = MutableLiveData<Boolean>()

    fun loginUser(inputId: String, inputPw: String, context: Context) {
//        _loginResult.value =
//            signUpUser != null && signUpUser.userId == inputId && signUpUser.userPw == inputPw

        authService.login(RequestLoginDto(inputId, inputPw))
            .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>,
                ) {
                    if (response.isSuccessful) {
                        val data: ResponseLoginDto =
                            response.body() ?: ResponseLoginDto(-1, "null", "null")
                        val userId = data.id

                        Toast.makeText(
                            context,
                            "로그인이 성공하였고 유저의 ID는 $userId 입니둥",
                            Toast.LENGTH_SHORT,
                        ).show()
                        _loginResult.value = true
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "ㅜ ㅜ 서버 에러 발생 ㅜ ㅜ",
                        Toast.LENGTH_SHORT,
                    ).show()

                    _loginResult.value = false
                }
            })
    }

    fun onClickLoginBtn() {
        isLoginButtonClicked.value = true
    }
    fun saveUserForAutoLogin(context: Context, signUpUser: User) {
        UserSharedPreferences.setUser(context, signUpUser)
    }
}
