package org.sopt.dosopttemplate.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.Friends
import org.sopt.dosopttemplate.databinding.ItemFriendBinding

class FriendsAdapter(context: Context) : RecyclerView.Adapter<FriendViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    private var friendList: List<Friends> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun getItemCount() = friendList.size

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFriendList(friendList: List<Friends>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}
