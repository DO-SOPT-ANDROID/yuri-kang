package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.data.DummyFriendsData
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.adapter.FriendsSealedAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // 뷰를 생성하기 위한 inflater, 담기 위한 container, 저장하기 위한 savedInstanceState
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var friendsSealedAdapter = FriendsSealedAdapter(requireContext())
        binding.rvFriends.adapter = friendsSealedAdapter
        friendsSealedAdapter.addFriendsData(ArrayList(DummyFriendsData.dummyFriendList))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
