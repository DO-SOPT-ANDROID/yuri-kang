package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.util.BackPressedUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = UserSharedPreferences.getUserID(this@MainActivity)
        val getNickname = UserSharedPreferences.getUserNickname(this@MainActivity)
        val getAge = UserSharedPreferences.getUserAge(this@MainActivity)

        binding.run {
            tvMainId.text = getId
            tvMainNickname.text = getNickname
            tvMainAge.text = getAge
        }

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(this@MainActivity)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val backPressedUtil = BackPressedUtil<ActivityMainBinding>(this)
        backPressedUtil.BackButton()
    }
}
