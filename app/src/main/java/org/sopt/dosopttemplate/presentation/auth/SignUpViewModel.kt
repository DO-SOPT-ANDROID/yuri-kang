package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.User

class SignUpViewModel : ViewModel() {
    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    fun signUpUser(signUpUser: User) {
        val isSignUpSuccessful = signUpUser.userId.length in 6..10 &&
            signUpUser.userPw.length in 9..11 && signUpUser.userNickname.isNotBlank() &&
            signUpUser.userAge.length in 1..2 || signUpUser.userAge != "0"

        _signUpResult.value = isSignUpSuccessful
    }
}
