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

        // 처음에 보여주어야 하는 프래그먼트를 변수로
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        // 만약 프래그먼트가 끼워지지 않았다면 프래그먼트메이저를 사용하여 끼워보기
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        } // Fragment의 재생성과 newInstance() 알아보기

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
}
