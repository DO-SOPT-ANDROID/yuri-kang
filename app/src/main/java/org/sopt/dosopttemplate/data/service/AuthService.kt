package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import org.sopt.dosopttemplate.data.remote.response.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 회원가입
    @POST("api/v1/members")
    fun signUp(
        @Body request: RequestSignupDto,
    ): Call<Void>

    // 로그인
    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}
