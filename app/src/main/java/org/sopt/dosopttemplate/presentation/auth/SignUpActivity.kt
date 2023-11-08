package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignupSignup.setOnClickListener {
            val signUpUserId = binding.etSignupId.text.toString()
            val signUpUserPw = binding.etSignupPw.text.toString()
            val signUpUserNickname = binding.etSignupNickname.text.toString()
            val signUpUserAge = binding.etSignupAge.text.toString()

            val signUpUser = User(signUpUserId, signUpUserNickname, signUpUserAge, signUpUserPw)

            // 필수 조건 미입력 시
            if (signUpUser.userId.isEmpty() || signUpUser.userPw.isEmpty() || signUpUser.userNickname.isEmpty() || signUpUser.userAge.isEmpty()) {
                showShortSnackBar(binding.root, getString(R.string.signup_fail))
            } else {
                // 필수 조건 모두 입력 시
                // 조건
                if (signUpUser.userId.length > 10 || signUpUser.userId.length < 6) {
                    showShortSnackBar(binding.root, getString(R.string.signup_id))
                } else if (signUpUser.userPw.length > 12 || signUpUser.userPw.length < 8) {
                    showShortSnackBar(binding.root, getString(R.string.signup_pw))
                } else if (signUpUser.userNickname.isBlank()) {
                    showShortSnackBar(binding.root, getString(R.string.signup_nickname))
                } else if (signUpUser.userAge.length >= 3 || signUpUser.userAge == "0") {
                    showShortSnackBar(binding.root, getString(R.string.signup_age))
                } else {
                    // 화면 전환
                    showShortToast(getString(R.string.signup_success))

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("signUpUser", signUpUser)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    Log.d("signUpUser 회원가입할때의 정보", signUpUser.toString())
                }
            }
        }
    }
}
