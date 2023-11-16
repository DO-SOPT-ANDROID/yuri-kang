package org.sopt.dosopttemplate.presentation.adapter.people

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import org.sopt.dosopttemplate.databinding.ItemPeopleBinding

class PeopleListAdapter : RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder>() {

    private var peopleList: ArrayList<ResponsePeopleListDto> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(people: ArrayList<ResponsePeopleListDto>) {
        peopleList = people
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)
        return PeopleListViewHolder(ItemPeopleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        val responsePeopleList = peopleList[position]
        holder.onBindView(responsePeopleList)
    }

    override fun getItemCount() = peopleList.size

    class PeopleListViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(peopleData: ResponsePeopleListDto) {
            binding.run {
                for (person in peopleData.data) {
                    ivPeopleProfile.load(person.avatar) {
                        placeholder(R.drawable.img_default_kakao_profile)
                        error(R.drawable.img_default_kakao_profile)
                    }
                    ivPeopleProfile.clipToOutline = true
                    tvPeopleId.text = person.id.toString()
                    tvPeopleName.text = "${person.firstName} ${person.lastName}"
                    tvPeopleEmail.text = person.email
                }
            }
        }
    }
}
