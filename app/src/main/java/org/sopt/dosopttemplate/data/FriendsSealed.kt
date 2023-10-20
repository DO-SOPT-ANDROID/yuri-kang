package org.sopt.dosopttemplate.data

import android.media.Image
import android.widget.Button

sealed class FriendsSealed {
    data class FriendsMe(
        val name: String,
        val plusDescription: Button,
        val profileImage: Image,
        val modifyProfile: Button,
    ) : FriendsSealed()

    data class FriendsNomal(
        val name: String,
        val description: String,
        val profileImage: Image,
    ) : FriendsSealed()

    data class FriendsMusic(
        val name: String,
        val description: String,
        val profileImage: Image,
    ) : FriendsSealed()

    data class FriendsBirthday(
        val name: String,
        val description: String,
        val profileImage: Image,
    ) : FriendsSealed()
}
