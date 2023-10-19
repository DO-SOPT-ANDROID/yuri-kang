package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Friends
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: Friends) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvFriendName.text = friendData.name
            tvFriendContent.text = friendData.content
        }
    }
}
