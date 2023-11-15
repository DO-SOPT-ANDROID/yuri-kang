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
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

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
                        val data: ResponseLoginDto = response.body()!!
                        val userId = data.id

                        Toast.makeText(
                            context,
                            "로그인이 성공하였고 유저의 ID는 $userId 입니둥",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "서버 에러 발생 ㅜ ㅜ",
                        Toast.LENGTH_SHORT,
                    ).show()

                    _loginResult.value = false
                }
            })
    }

    fun saveUserForAutoLogin(context: Context, signUpUser: User) {
        UserSharedPreferences.setUser(context, signUpUser)
    }
}
