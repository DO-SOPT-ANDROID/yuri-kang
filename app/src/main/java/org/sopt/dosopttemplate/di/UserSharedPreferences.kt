package org.sopt.dosopttemplate.di

import android.content.Context
import android.content.SharedPreferences
import org.sopt.dosopttemplate.data.User

object UserSharedPreferences {
    private const val MY_ACCOUNT: String = "account"
    const val USER_KEY = "USER_KEY"

    fun setUser(context: Context, user: User) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(
            USER_KEY,
            "${user.userId},${user.userPw},${user.userNickname}",
        )
        editor.apply()
    }

    fun getUser(context: Context): User {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val userData = prefs.getString(USER_KEY, "") ?: ""
        val userDataParts = userData.split(",")
        return if (userDataParts.size == 3) {
            User(userDataParts[0], userDataParts[1], userDataParts[2])
        } else {
            User("", "", "")
        }
    }

    fun clearUser(context: Context) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}
