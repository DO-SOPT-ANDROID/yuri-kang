package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.presentation.main.BnvActivity
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

        // 자동 로그인으로 저장된 유저 정보
        val spUser = UserSharedPreferences.getUser(this)
        Log.d("spUser 자동로그인 됐던 경우", spUser.toString())

        // 자동 로그인이 된 경우
        if (spUser.userId.isNotBlank()) {
            val intent = Intent(this, BnvActivity::class.java)
                .addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            // 새로운 Activity를 수행하고 현재 Activity를 스텍에서 제거
            startActivity(intent)
        }

        // 회원가입 하러 가기
        binding.btnSignupSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 로그인 하기
        binding.btnLoginLogin.setOnClickListener {
            // 자동 로그인이 적용되지 않고, 회원가입에서 넘어온 경우
            val signUpUser = intent.getParcelableExtra<User>("signUpUser")

            Log.d("spUser 회원가입에서 넘어온 경우", signUpUser.toString())

            val inputId = binding.etSignupId.text.toString()
            val inputPw = binding.etSignupPw.text.toString()

            if (signUpUser != null && signUpUser.userId == inputId && signUpUser.userPw == inputPw) {
                showShortToast(getString(R.string.login_success))

                // 자동 로그인
                if (binding.cbLoginAutologin.isChecked) {
                    UserSharedPreferences.setUser(this, signUpUser)
                }

                val intent = Intent(this, BnvActivity::class.java)
                intent.putExtra("signUpUser", signUpUser)
                startActivity(intent)
                finish()
            } else {
                showShortSnackBar(binding.root, getString(R.string.login_fail))
            }
        }

        val backPressedUtil = BackPressedUtil<ActivityLoginBinding>(this)
        backPressedUtil.BackButton()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }
}
