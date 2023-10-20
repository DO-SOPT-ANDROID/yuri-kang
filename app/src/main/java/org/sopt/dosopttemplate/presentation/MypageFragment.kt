package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.presentation.auth.LoginActivity

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

        val spId = UserSharedPreferences.getUserID(requireContext())
        val spNickname = UserSharedPreferences.getUserNickname(requireContext())
        val spAge = UserSharedPreferences.getUserAge(requireContext())

        val bundle = arguments
        val getId = bundle?.getString("userId")
        val getNickname = bundle?.getString("userNickname")
        val getAge = bundle?.getString("userAge")

        // 자동 로그인이 된 경우
        if (UserSharedPreferences.getUserID(requireContext()).isNotBlank()
        ) {
            binding.run {
                tvMainId.text = spId
                tvMainNickname.text = spNickname
                tvMainAge.text = spAge
            }
        } else {
            binding.run {
                tvMainId.text = getId
                tvMainNickname.text = getNickname
                tvMainAge.text = getAge
            }
        }

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(requireContext())

            // 프래그먼트를 제거
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            fragmentManager
                .beginTransaction()
                .remove(this)
                .commit()

            // 로그인 액티비티로 이동
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}