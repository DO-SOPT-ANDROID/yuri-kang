package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.remote.response.ResponseCheckUsernameDto
import org.sopt.dosopttemplate.data.remote.response.ResponseMemberGetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    // 회원 정보 조회
    @GET("api/v1/members/{memberId}")
    fun userInfoGet(
        @Path("memberId") memberId: Int,
    ): Call<ResponseMemberGetDto>

    // 닉네임 중복 조회
    @GET("api/v1/members/check")
    fun checkUsernameExistence(
        @Query("username") username: String,
    ): Call<ResponseCheckUsernameDto>
}
