package org.sopt.dosopttemplate.presentation.main.peoplelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentPeopleListBinding
import org.sopt.dosopttemplate.presentation.adapter.people.PeopleListAdapter
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.showShortSnackBar

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

        peopleListViewModel.peopleData.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    showShortSnackBar(binding.root, getString(R.string.uistate_loading))
                }

                is UiState.Success -> {
                    showShortSnackBar(binding.root, getString(R.string.uistate_find_friends))
                    val peopleList = uiState.data
                    peopleListAdapter.setUsers(ArrayList(peopleList))
                }

                is UiState.Failure -> {
                    showShortSnackBar(binding.root, "정보 불러오기 실패 : ${uiState.errorMessage}")
                }

                is UiState.Initial -> {
                    showShortSnackBar(binding.root, getString(R.string.uistate_initial))
                }
            }
        }.launchIn(lifecycleScope)

        peopleListViewModel.fetchPeopleData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvPeople.adapter = null
        _binding = null
    }
}
