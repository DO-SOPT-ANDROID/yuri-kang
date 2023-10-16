package org.sopt.dosopttemplate.di

import android.content.Context
import android.content.SharedPreferences

object UserSharedPreferences {
    private val MY_ACCOUNT: String = "account"
    const val USER_ID = "USER_ID"
    const val USER_PW = "USER_PW"
    const val USER_NICKNAME = "USER_NICKNAME"
    const val USER_AGE = "USER_AGE"

    fun setUserID(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(USER_ID, input)
        editor.apply()
    }

    fun getUserID(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString(USER_ID, "").toString()
    }

    fun setUserPw(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(USER_PW, input)
        editor.apply()
    }

    fun getUserPw(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString(USER_PW, "").toString()
    }

    fun setUserNickname(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(USER_NICKNAME, input)
        editor.apply()
    }

    fun getUserNickname(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString(USER_NICKNAME, "").toString()
    }

    fun setUserAge(context: Context, input: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(USER_AGE, input)
        editor.apply()
    }

    fun getUserAge(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString(USER_AGE, "").toString()
    }

    fun clearUser(context: Context) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}
