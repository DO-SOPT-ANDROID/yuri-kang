package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences

class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding: FragmentMypageBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
        // 뷰를 생성하기 위한 inflater, 담기 위한 container, 저장하기 위한 savedInstanceState
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getId = UserSharedPreferences.getUserID(requireContext())
        val getNickname = UserSharedPreferences.getUserNickname(requireContext())
        val getAge = UserSharedPreferences.getUserAge(requireContext())

        binding.run {
            tvMainId.text = getId
            tvMainNickname.text = getNickname
            tvMainAge.text = getAge
        }

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(requireContext())


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
