package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityBnvBinding
import org.sopt.dosopttemplate.presentation.main.android.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.main.home.HomeFragment
import org.sopt.dosopttemplate.presentation.main.mypage.MypageFragment
import org.sopt.dosopttemplate.presentation.main.peoplelist.PeopleListFragment
import org.sopt.dosopttemplate.util.BackPressedUtil
import org.sopt.dosopttemplate.util.showShortSnackBarAction

class BnvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBnvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBnvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialFragment(R.id.fcv_home, HomeFragment())

        clickBnv()
        clickTopAppBar()

        scrollListener(RecyclerView(this))
    }

    private fun clickBnv() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val fragment = HomeFragment.newInstance()
                    replaceFragment(fragment)
                    true
                }

                R.id.menu_do_android -> {
                    val fragment = DoAndroidFragment.newInstance()
                    replaceFragment(fragment)
                    true
                }

                R.id.menu_people -> {
                    val fragment = PeopleListFragment.newInstance()
                    replaceFragment(fragment)
                    true
                }

                R.id.menu_mypage -> {
                    val signUpUser = intent.getParcelableExtra<User>("signUpUser")

                    // 자동 로그인이 아닌 경우 프래그먼트로 유저 정보 전달
                    val fragment = MypageFragment.newInstance(signUpUser)

                    replaceFragment(fragment)
                    true
                }

                else -> false
            }
        }
        binding.bnvHome.selectedItemId = R.id.menu_home

        val backPressedUtil = BackPressedUtil<ActivityBnvBinding>(this)
        backPressedUtil.BackButton()
    }

    private fun clickTopAppBar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tab_menu_modify -> {
                    showShortSnackBarAction(
                        binding.root,
                        getString(R.string.top_app_bar_toast_modify),
                        "확인",
                    )
                    true
                }

                R.id.tab_menu_favorite -> {
                    showShortSnackBarAction(
                        binding.root,
                        getString(R.string.top_app_bar_toast_favorite),
                        "확인",
                    )
                    true
                }

                R.id.tab_menu_share -> {
                    showShortSnackBarAction(
                        binding.root,
                        getString(R.string.top_app_bar_toast_share),
                        "확인",
                    )
                    true
                }

                else -> false
            }
        }
    }

    fun scrollListener(view: RecyclerView?) {
        binding.bnvHome.setOnClickListener {
            view?.smoothScrollToPosition(0)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

    // 프래그먼트 초기화 함수
    private fun initialFragment(containerViewId: Int, fragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(containerViewId)

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(containerViewId, fragment)
                .commit()
        }
    }
}
