package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID")
        val getNickname = intent.getStringExtra("Nickname")
        val getAge = intent.getStringExtra("Age")

        binding.tvMainId.text = getId
        binding.tvMainNickname.text = getNickname
        binding.tvMainAge.text = getAge
    }
}
