package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.remote.request.RequestLoginDto
import org.sopt.dosopttemplate.data.remote.request.RequestSignupDto
import org.sopt.dosopttemplate.data.remote.response.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    // 회원가입
    @POST("api/v1/members")
    suspend fun signUp(
        @Body request: RequestSignupDto,
    )

    // 로그인
    @POST("api/v1/members/sign-in")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): ResponseLoginDto
}
