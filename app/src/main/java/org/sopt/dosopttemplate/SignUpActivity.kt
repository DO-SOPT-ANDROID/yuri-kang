package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
