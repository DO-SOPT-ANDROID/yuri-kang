package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.BackPressedUtil
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignupSignup.setOnClickListener {
            val userId = binding.etSignupId.text.toString()
            val userPw = binding.etSignupPw.text.toString()
            val userNickname = binding.etSignupNickname.text.toString()
            val userAge = binding.etSignupAge.text.toString()

            // 필수 조건 미입력 시
            if (userId.isEmpty() || userPw.isEmpty() || userNickname.isEmpty() || userAge.isEmpty()) {
                showShortSnackBar(binding.root, getString(R.string.signup_fail))
            } else {
                // 필수 조건 모두 입력 시
                // 조건
                if (userId.length > 10 || userId.length < 6) {
                    showShortSnackBar(binding.root, getString(R.string.signup_id))
                } else if (userPw.length > 12 || userPw.length < 8) {
                    showShortSnackBar(binding.root, getString(R.string.signup_pw))
                } else if (userNickname.isBlank()) {
                    showShortSnackBar(binding.root, getString(R.string.signup_nickname))
                } else if (userAge.length >= 3 || userAge == "0") {
                    showShortSnackBar(binding.root, getString(R.string.signup_age))
                } else {
                    // 화면 전환
                    showShortToast(getString(R.string.signup_success))

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("ID", userId)
                    intent.putExtra("PW", userPw)
                    intent.putExtra("Nickname", userNickname)
                    intent.putExtra("Age", userAge)
                    startActivity(intent)
                    finish()
                }
            }
        }
        // 키보드 내리기
        hideKeyboard(binding.root)

        val backPressedUtil = BackPressedUtil<ActivitySignupBinding>(this)
        backPressedUtil.BackButton()
    }
}
