package org.sopt.dosopttemplate.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseCheckUsernameDto(
    @SerialName("isExist")
    val userNameIsExist: Boolean,
)
