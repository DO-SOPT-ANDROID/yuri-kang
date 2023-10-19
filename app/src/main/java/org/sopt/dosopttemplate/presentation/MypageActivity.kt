package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMypageBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.presentation.auth.LoginActivity
import org.sopt.dosopttemplate.util.BackPressedUtil

class MypageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMypageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = UserSharedPreferences.getUserID(this@MypageActivity)
        val getNickname = UserSharedPreferences.getUserNickname(this@MypageActivity)
        val getAge = UserSharedPreferences.getUserAge(this@MypageActivity)

        binding.run {
            tvMainId.text = getId
            tvMainNickname.text = getNickname
            tvMainAge.text = getAge
        }

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(this@MypageActivity)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val backPressedUtil = BackPressedUtil<ActivityMypageBinding>(this)
        backPressedUtil.BackButton()
    }
}
