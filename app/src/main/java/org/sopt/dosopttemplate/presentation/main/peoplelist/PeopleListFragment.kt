package org.sopt.dosopttemplate.presentation.main.peoplelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.dosopttemplate.databinding.FragmentPeopleListBinding
import org.sopt.dosopttemplate.presentation.adapter.people.PeopleListAdapter

class PeopleListFragment : Fragment() {
    private val peopleListViewModel by viewModels<PeopleListViewModel>()
    private lateinit var peopleListAdapter: PeopleListAdapter

    companion object {
        fun newInstance(): PeopleListFragment {
            return PeopleListFragment()
        }
    }

    private var _binding: FragmentPeopleListBinding? = null
    private val binding: FragmentPeopleListBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peopleListAdapter = PeopleListAdapter()

        binding.rvPeople.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPeople.adapter = peopleListAdapter

        peopleListViewModel.peopleData.observe(
            viewLifecycleOwner,
            Observer { peopleList ->
                // Log.d("PeopleList 리스트 .. 왜 1개만 보일까융", "list size: ${peopleList.size}")
                peopleListAdapter.setUsers(ArrayList(peopleList))
            },
        )

        peopleListViewModel.fetchPeopleData(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPeople.adapter = null
        _binding = null
    }
}
