package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
    }
}
