package org.sopt.dosopttemplate.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed

class FriendsSealedAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var friendList: ArrayList<FriendsSealed> = ArrayList()

    fun submitData(list: ArrayList<FriendsSealed>) {
        friendList.clear()
        friendList.addAll(list)
    }

    override fun getItemCount() = friendList.size

    override fun getItemViewType(position: Int) = when (friendList[position]) {
        is FriendsSealed.FriendsMe -> R.layout.item_friends_me
        is FriendsSealed.FriendsNomal -> R.layout.item_friends_nomal
        is FriendsSealed.FriendsMusic -> R.layout.item_friends_music
        is FriendsSealed.FriendsBirthday -> R.layout.item_friends_birthday
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)

        // 타입별 뷰 홀더 레이아웃 지정
        return when (viewType) {
            R.layout.item_friends_me -> FriendsMeViewHolder(
                getViewDataBinding(
                    parent,
                    R.layout.item_friends_me,
                ),
            )

            R.layout.item_friends_music -> FriendsMusicViewHolder(
                getViewDataBinding(
                    parent,
                    R.layout.item_friends_music,
                ),
            )

            R.layout.item_friends_birthday -> FriendsBirthdayViewHolder(
                getViewDataBinding(
                    parent,
                    R.layout.item_friends_birthday,
                ),
            )

            else -> FriendsNomalViewHolder(
                getViewDataBinding(
                    parent,
                    R.layout.item_friends_nomal,
                ),
            ) // nomal의 경우
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendList[position]

        when (holder) {
            is FriendsMeViewHolder -> holder.onBindView(item as FriendsSealed.FriendsMe)
            is FriendsMusicViewHolder -> holder.onBindView(item as FriendsSealed.FriendsMusic)
            is FriendsBirthdayViewHolder -> holder.onBindView(item as FriendsSealed.FriendsBirthday)
            is FriendsNomalViewHolder -> holder.onBindView(item as FriendsSealed.FriendsNomal)
        }
    }

    private fun <T : ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutRes: Int): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false,
        )
    }
}
