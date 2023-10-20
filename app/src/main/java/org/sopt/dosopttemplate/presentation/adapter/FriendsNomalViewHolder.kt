package org.sopt.dosopttemplate.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.ItemFriendsNomalBinding

class FriendsNomalViewHolder(private val binding: ItemFriendsNomalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBindView(friendsNomalData: FriendsSealed.FriendsNomal) {
        binding.run {
            ivFriendsNomalProfile.setImageResource(friendsNomalData.profileImage)
            tvFriendsNomalName.text = friendsNomalData.name
            tvFriendsNomalContent.text = friendsNomalData.description
        }
    }
}
