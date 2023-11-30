package org.sopt.dosopttemplate.presentation.adapter.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsMusicBinding

class FriendsMusicViewHolder(private val binding: ItemFriendsMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsMusicData: FriendsSealed.FriendsMusic) {
        binding.run {
            ivFriendsMusicProfile.load(friendsMusicData.profileImage) {
                placeholder(R.drawable.img_default_kakao_profile)
                error(R.drawable.img_default_kakao_profile) // Load Error 시 보여줄 이미지
            }
            ivFriendsMusicProfile.clipToOutline = true
            tvFriendsMusicName.text = friendsMusicData.name
            tvFriendsMusicContent.text = friendsMusicData.description
            tvFriendsMusicMusic.text = friendsMusicData.music
        }
    }
}
