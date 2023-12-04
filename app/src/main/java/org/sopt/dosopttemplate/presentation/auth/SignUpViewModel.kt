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
