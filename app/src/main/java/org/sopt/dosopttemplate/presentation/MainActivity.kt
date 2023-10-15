package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.util.BackPressedUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var callback: OnBackPressedCallback
    var delayTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = UserSharedPreferences.getUserID(this@MainActivity)
        val getNickname = UserSharedPreferences.getUserNickname(this@MainActivity)
        val getAge = UserSharedPreferences.getUserAge(this@MainActivity)

        binding.tvMainId.text = getId
        binding.tvMainNickname.text = getNickname
        binding.tvMainAge.text = getAge

        // 로그아웃
        binding.btnMainLogout.setOnClickListener {
            UserSharedPreferences.clearUser(this@MainActivity)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val backPressedUtil = BackPressedUtil(this)
        backPressedUtil.BackButton()
    }
}
