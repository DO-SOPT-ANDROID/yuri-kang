package org.sopt.dosopttemplate.data

import org.sopt.dosopttemplate.R

object dummyFriendsData {
    val dummyFriendList = listOf(
        FriendsSealed.FriendsMe(
            profileImage = R.drawable.img_main_profile,
            name = "강유리",
        ),
        FriendsSealed.FriendsNomal(
            profileImage = R.drawable.img_default_kakao_profile,
            name = "SOPT YB 김민우",
            description = "떼잉",
        ),
        FriendsSealed.FriendsBirthday(
            profileImage = R.drawable.img_lion1,
            name = "SOPT OB 라이언1",
            description = "생일 선물은 돈으로 주세용",
        ),
        FriendsSealed.FriendsMusic(
            profileImage = R.drawable.img_default_kakao_profile,
            name = "친구3...",
            description = "무식이즈 마이 라이프",
            music = "뮤지크 - 가아수",
        ),
    )
}
