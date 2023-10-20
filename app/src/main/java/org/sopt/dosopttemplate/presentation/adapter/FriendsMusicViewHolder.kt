package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsMusicBinding

class FriendsMusicViewHolder(private val binding: ItemFriendsMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsMusicData: FriendsSealed.FriendsMusic) {
        binding.run {
            ivFriendsMusicProfile.setImageResource(friendsMusicData.profileImage)
            tvFriendsMeName.text = friendsMusicData.name
            tvFriendsMeContent.text = friendsMusicData.description
        }
    }
}
