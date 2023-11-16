package org.sopt.dosopttemplate.presentation.adapter.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsBirthdayBinding

class FriendsBirthdayViewHolder(private val binding: ItemFriendsBirthdayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsBirthdayData: FriendsSealed.FriendsBirthday) {
        binding.run {
            ivFriendsBirthdayProfile.load(friendsBirthdayData.profileImage) {
                placeholder(R.drawable.img_default_kakao_profile)
                error(R.drawable.img_default_kakao_profile) // Load Error 시 보여줄 이미지
            }
            ivFriendsBirthdayProfile.clipToOutline = true
            tvFriendsBirthdayName.text = friendsBirthdayData.name
            tvFriendsBirthdayContent.text = friendsBirthdayData.description
        }
    }
}
