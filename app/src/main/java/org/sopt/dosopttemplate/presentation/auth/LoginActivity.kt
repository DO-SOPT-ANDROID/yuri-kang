package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences
import org.sopt.dosopttemplate.presentation.main.BnvActivity
import org.sopt.dosopttemplate.util.BackPressedUtil
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.showShortSnackBar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 자동 로그인으로 저장된 유저 정보
        val spUser = UserSharedPreferences.getUser(this)

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

            val inputId = binding.etSignupId.text.toString()
            val inputPw = binding.etSignupPw.text.toString()

            loginViewModel.loginUser(inputId, inputPw)

            loginViewModel.loginResult.observe(
                this,
            ) { uiState ->
                when (uiState) {
                    is UiState.Success -> {
                        if (binding.cbLoginAutologin.isChecked) {
                            signUpUser?.let {
                                loginViewModel.saveUserForAutoLogin(this, it)
                            }
                        }
                        val intent = Intent(this, BnvActivity::class.java)
                        intent.putExtra("signUpUser", signUpUser)
                        startActivity(intent)
                        finish()
                    }

                    is UiState.Failure -> {
                        //showShortSnackBar(binding.root, "로그인 성공, 유저 아이디 : ${loginViewModel.}")
                    }

                    is UiState.Loading -> {
                        showShortSnackBar(binding.root, "로딩중")
                    }
                }
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
