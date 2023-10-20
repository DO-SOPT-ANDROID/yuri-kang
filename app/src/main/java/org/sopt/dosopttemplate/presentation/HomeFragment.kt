package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.FriendsSealed
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.adapter.FriendsSealedAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    private val mockFriendList = ArrayList<FriendsSealed>().apply {
        add(
            FriendsSealed.FriendsMe(
                profileImage = R.drawable.img_main_profile,
                name = "강유리",
            ),
        )
        add(
            FriendsSealed.FriendsNomal(
                profileImage = R.drawable.ic_launcher_foreground,
                name = "친구1",
                description = "떼잉",
            ),
        )
        add(
            FriendsSealed.FriendsBirthday(
                profileImage = R.drawable.img_cake,
                name = "친구2...",
                description = "생일임",
            ),
        )
        add(
            FriendsSealed.FriendsMusic(
                profileImage = R.drawable.ic_launcher_foreground,
                name = "친구3...",
                description = "무식이즈 마이 라이프",
                music = "뮤지크 - 가아수",
            ),
        )
    }

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
        friendsSealedAdapter.addFriendsData(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
