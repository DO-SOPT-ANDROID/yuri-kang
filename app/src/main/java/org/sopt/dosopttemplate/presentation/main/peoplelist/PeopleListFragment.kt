package org.sopt.dosopttemplate.presentation.main.peoplelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentPeopleListBinding

class PeopleListFragment : Fragment() {

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
}
