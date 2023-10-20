package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsBirthdayBinding

class FriendsBirthdayViewHolder(private val binding: ItemFriendsBirthdayBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsBirthdayData: FriendsSealed.FriendsBirthday) {
        binding.run {
            ivFriendsBirthdayProfile.setImageResource(friendsBirthdayData.profileImage)
            ivFriendsBirthdayProfile.clipToOutline = true
            tvFriendsBirthdayName.text = friendsBirthdayData.name
            tvFriendsBirthdayContent.text = friendsBirthdayData.description
        }
    }
}
