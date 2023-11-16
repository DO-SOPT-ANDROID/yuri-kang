package org.sopt.dosopttemplate.presentation.adapter.people

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.PeopleList
import org.sopt.dosopttemplate.databinding.ItemPeopleBinding

class PeopleListAdapter : RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder>() {

    private var peopleList: ArrayList<PeopleList> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(people: ArrayList<PeopleList>) {
        peopleList = people
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)
        return PeopleListViewHolder(ItemPeopleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        val user = peopleList[position]
        holder.onBindView(user)
    }

    override fun getItemCount() = peopleList.size

    class PeopleListViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(PeopleData: PeopleList) {
            binding.run {
                ivPeopleProfile.load(PeopleData.avatar) {
                    placeholder(R.drawable.img_default_kakao_profile)
                    error(R.drawable.img_default_kakao_profile)
                }
                ivPeopleProfile.clipToOutline = true
                tvPeopleId.text = PeopleData.id.toString()
                tvPeopleName.text = PeopleData.firstName + PeopleData.lastName
                tvPeopleEmail.text = PeopleData.email
            }
        }
    }
}
