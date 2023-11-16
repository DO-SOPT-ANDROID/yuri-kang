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

//    fun signUpUser(signUpUser: User) {
//        val isSignUpSuccessful = signUpUser.userId.length in 6..10 &&
//            signUpUser.userPw.length in 9..11 && signUpUser.userNickname.isNotBlank() &&
//            signUpUser.userAge.length in 1..2 || signUpUser.userAge != "0"
//
//        _signUpResult.value = isSignUpSuccessful
//    }

    fun signUpUser(signUpUser: User, context: Context) {
        ServicePool.authService.signUp(
            RequestSignupDto(
                signUpUser.userId,
                signUpUser.userPw,
                signUpUser.userNickname,
            ),
        )
            .enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>,
                ) {
                    if (response.isSuccessful) {
                        _signUpResult.value = true

                        Toast.makeText(
                            context,
                            "회원가입 성공",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
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
