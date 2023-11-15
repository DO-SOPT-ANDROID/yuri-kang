package org.sopt.dosopttemplate.data.remote.response

import kotlinx.serialization.SerialName

class ResponseCheckUsernameDto(
    @SerialName("isExist")
    val userNameIsExist: Boolean,
)
