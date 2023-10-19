package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding

class DoAndroidFragment : Fragment() {

    private var _binding: FragmentDoAndroidBinding? = null
    private val binding: FragmentDoAndroidBinding
        get() = requireNotNull(_binding) { "바인딩 error" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDoAndroidBinding.inflate(inflater, container, false)
        return binding.root
        // 뷰를 생성하기 위한 inflater, 담기 위한 container, 저장하기 위한 savedInstanceState
    }
}
