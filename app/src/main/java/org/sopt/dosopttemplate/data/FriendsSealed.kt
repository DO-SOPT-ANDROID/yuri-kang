package org.sopt.dosopttemplate.data

sealed class FriendsSealed {
    data class FriendsMe(
        val name: String,
        val profileImage: Int,
    ) : FriendsSealed()

    data class FriendsNomal(
        val name: String,
        val description: String?,
        val profileImage: Int,
    ) : FriendsSealed()

    data class FriendsMusic(
        val name: String,
        val description: String?,
        val profileImage: Int,
        val music: String
    ) : FriendsSealed()

    data class FriendsBirthday(
        val name: String,
        val description: String?,
        val profileImage: Int,
    ) : FriendsSealed()
}
