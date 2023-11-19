package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignupSignup.setOnClickListener {
            val signUpUserId = binding.etSignupId.text.toString()
            val signUpUserPw = binding.etSignupPw.text.toString()
            val signUpUserNickname = binding.etSignupNickname.text.toString()
            // val signUpUserAge = binding.etSignupAge.text.toString()

            val signUpUser = User(signUpUserId, signUpUserPw, signUpUserNickname)

            signUpViewModel.signUpUser(signUpUser, this)

            signUpViewModel.signUpResult.observe(this) { signUpSuccessful ->
                if (signUpSuccessful) {
                    // 화면 전환
                    showShortToast(getString(R.string.signup_success))

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("signUpUser", signUpUser)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                } else {
                    showShortSnackBar(binding.root, getString(R.string.signup_fail))
                }
            }
        }
    }
}
