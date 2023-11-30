package org.sopt.dosopttemplate.data.remote.response

import kotlinx.serialization.SerialName

data class ResponseMemberGetDto(
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
)
