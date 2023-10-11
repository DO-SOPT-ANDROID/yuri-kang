package org.sopt.dosopttemplate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID")
        val getPw = intent.getStringExtra("PW")
        val getNickname = intent.getStringExtra("Nickname")
        val getAge = intent.getStringExtra("Age")
        Log.d("받은 값 확인", "$getId $getPw $getNickname $getAge")
    }
}
