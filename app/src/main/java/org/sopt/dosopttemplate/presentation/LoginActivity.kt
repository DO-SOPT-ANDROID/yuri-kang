package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.util.BackPressedUtil
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID")
        val getPw = intent.getStringExtra("PW")
        val getNickname = intent.getStringExtra("Nickname")
        val getAge = intent.getStringExtra("Age")

        // 자동 로그인이 된 경우
        if (UserSharedPreferences.getUserID(this).isNotBlank() ||
            UserSharedPreferences.getUserPw(this).isNotBlank()
        ) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 회원가입 하러 가기
        binding.btnSignupSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 로그인 하기
        binding.btnLoginLogin.setOnClickListener {
            if (binding.etSignupId.text.toString() == getId && binding.etSignupPw.text.toString() == getPw) {
                showShortToast(getString(R.string.login_success))

                // 자동 로그인
                if (binding.cbLoginAutologin.isChecked) {
                    // 유저 정보 저장
                    UserSharedPreferences.apply {
                        setUserID(this@LoginActivity, getId)
                        setUserPw(this@LoginActivity, getPw)
                        setUserNickname(this@LoginActivity, getNickname!!)
                        setUserAge(this@LoginActivity, getAge!!)
                    }
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showShortSnackBar(binding.root, getString(R.string.login_fail))
            }
        }
        // 키보드 내리기
        hideKeyboard(binding.root)

        val backPressedUtil = BackPressedUtil<ActivityLoginBinding>(this)
        backPressedUtil.BackButton()
    }
}
