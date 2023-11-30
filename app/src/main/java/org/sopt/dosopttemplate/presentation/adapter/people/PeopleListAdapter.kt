package org.sopt.dosopttemplate.presentation.adapter.people

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.remote.response.PersonDto
import org.sopt.dosopttemplate.data.remote.response.ResponsePeopleListDto
import org.sopt.dosopttemplate.databinding.ItemPeopleBinding

class PeopleListAdapter : RecyclerView.Adapter<PeopleListAdapter.PeopleListViewHolder>() {

    private var peopleList: List<PersonDto> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(people: ArrayList<ResponsePeopleListDto>) {
        peopleList = people.flatMap { it.data }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)
        return PeopleListViewHolder(ItemPeopleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        val person = peopleList[position]
        holder.onBindView(person)
    }

    override fun getItemCount() = peopleList.size

    class PeopleListViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBindView(person: PersonDto) {
            binding.run {
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
