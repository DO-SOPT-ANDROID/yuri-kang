package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {

    // 사람들 리스트 받아오기
    @GET("users")
    suspend fun PeopleListGet(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): ResponsePeopleListDto
}
