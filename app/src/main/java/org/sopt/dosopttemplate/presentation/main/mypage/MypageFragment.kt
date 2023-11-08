package org.sopt.dosopttemplate.presentation.main.mypage

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.FragmentMypageBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.presentation.auth.LoginActivity

class MypageFragment : Fragment() {

    companion object {
        fun newInstance(user: User?): MypageFragment {
            val fragment = MypageFragment()
            val args = Bundle()
            args.putParcelable("user", user)
            fragment.arguments = args
            return fragment
        }
    }

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

        // 유저 데이터 초기화
        val spUser = UserSharedPreferences.getUser(requireContext())
        val bundle = arguments
        val signUpUser = bundle?.getParcelable<User>("signUpUser")

        // 자동 로그인이 된 경우
        if (spUser.userId.isNotBlank()) {
            binding.run {
                tvMainId.text = spUser.userId
                tvMainNickname.text = spUser.userNickname
                tvMainAge.text = spUser.userAge
            }
        } else {
            // 자동 로그인으로 저장된 유저 정보가 없는 경우
            signUpUser?.let {
                binding.run {
                    tvMainId.text = it.userId
                    tvMainNickname.text = it.userNickname
                    tvMainAge.text = it.userAge
                }
            }
        }

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(requireContext())

            // 로그인 액티비티로 이동
            val intent = Intent(requireContext(), LoginActivity::class.java)
                .addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
