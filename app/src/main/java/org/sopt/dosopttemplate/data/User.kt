package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String,
    val userNickname: String,
    val userAge: String,
    val userPw: String,
) : Parcelable
