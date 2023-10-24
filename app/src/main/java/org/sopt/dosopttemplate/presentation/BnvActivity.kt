package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityBnvBinding
import org.sopt.dosopttemplate.util.BackPressedUtil

class BnvActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBnvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBnvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialFragment(R.id.fcv_home, HomeFragment())

        clickBnv()
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

                R.id.menu_mypage -> {
                    val getId = intent.getStringExtra("ID")!!
                    val getNickname = intent.getStringExtra("Nickname")!!
                    val getAge = intent.getStringExtra("Age")!!

                    // 자동 로그인이 아닌 경우 프래그먼트로 유저 정보 전달
                    val fragment = MypageFragment.newInstance(getId, getNickname, getAge)

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
                .add(containerViewId, Fragment())
                .commit()
        }
    }
}
