package org.sopt.dosopttemplate.presentation.main.mypage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.di.UserSharedPreferences

class MypageViewModel : ViewModel() {
    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    fun loadUserData(context: Context) {
        val spUser = UserSharedPreferences.getUser(context)
        _userData.value = spUser
    }

    fun clearUserData(context: Context) {
        UserSharedPreferences.clearUser(context)
        _userData.value = User("", "", "", "")
    }
}
