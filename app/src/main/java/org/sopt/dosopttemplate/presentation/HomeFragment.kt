package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.Friends
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.adapter.FriendsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    private val mockFriendList = listOf(
        Friends(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "상호야...",
            content = "잘하고 있어. 금지",
        ),
        Friends(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "아오 상호시치",
            content = "앞으로 7주간 금주",
        ),
        Friends(
            profileImage = R.drawable.ic_launcher_foreground,
            name = "상호야...",
            content = "잘하고 있어. 금지",
        ),
    )

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
        // 대부분의 로직이 여기서 이루어짐

        val friendAdapter = FriendsAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
