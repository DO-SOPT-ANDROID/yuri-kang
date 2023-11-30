package org.sopt.dosopttemplate.presentation.adapter.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsMeBinding

class FriendsMeViewHolder(private val binding: ItemFriendsMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsMeData: FriendsSealed.FriendsMe) {
        binding.run {
            ivFriendsMeProfile.load(friendsMeData.profileImage) {
                placeholder(R.drawable.img_default_kakao_profile)
                error(R.drawable.img_default_kakao_profile) // Load Error 시 보여줄 이미지
            }
            ivFriendsMeProfile.clipToOutline = true
            tvFriendsMeName.text = friendsMeData.name
        }
    }
}
