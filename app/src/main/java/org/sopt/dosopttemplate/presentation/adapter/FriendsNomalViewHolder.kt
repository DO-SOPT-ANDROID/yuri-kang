package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsNomalBinding

class FriendsNomalViewHolder(private val binding: ItemFriendsNomalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsNomalData: FriendsSealed.FriendsNomal) {
        binding.run {
            ivFriendsNomalProfile.load(friendsNomalData.profileImage) {
                placeholder(R.drawable.img_default_kakao_profile)
                error(R.drawable.img_default_kakao_profile) // Load Error 시 보여줄 이미지
            }
            ivFriendsNomalProfile.setImageResource(friendsNomalData.profileImage)
            ivFriendsNomalProfile.clipToOutline = true
            tvFriendsNomalName.text = friendsNomalData.name
            tvFriendsNomalContent.text = friendsNomalData.description
        }
    }
}
