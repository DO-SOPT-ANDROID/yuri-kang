package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsMeBinding

class FriendsMeViewHolder(private val binding: ItemFriendsMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsMeData: FriendsSealed.FriendsMe) {
        binding.run {
            ivFriendsMeProfile.setImageResource(friendsMeData.profileImage)
            ivFriendsMeProfile.clipToOutline = true
            tvFriendsMeName.text = friendsMeData.name
        }
    }
}
