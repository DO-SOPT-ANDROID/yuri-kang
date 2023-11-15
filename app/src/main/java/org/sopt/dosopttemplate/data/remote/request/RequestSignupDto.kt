package org.sopt.dosopttemplate.data.remote.request

import kotlinx.serialization.SerialName

data class RequestSignupDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
)
