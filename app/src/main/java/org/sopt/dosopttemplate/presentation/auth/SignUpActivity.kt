package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
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

        binding.lifecycleOwner = this
        binding.authViewModel = signUpViewModel

        observeValid()
        clickSignUpBtn()
    }

    private fun observeValid() {
        signUpViewModel.idFlag.observe(this) { idFlag ->
            if (idFlag) {
                // signUpUserId = binding.tieEtSignupId.text.toString()
                binding.telSignupId.error = null
            } else {
                binding.telSignupId.error = "아이디는 영문, 숫자 포함입니다."
            }
            btnEnable()
        }
        signUpViewModel.pwFlag.observe(this) { pwFlag ->
            if (pwFlag) {
                // signUpUserPw = binding.tieEtSignupPw.text.toString()
                binding.telSignupPw.error = null
            } else {
                binding.telSignupPw.error = "아이디는 영문, 숫자 포함입니다."
            }
            btnEnable()
        }
        signUpViewModel.nicknameFlag.observe(this) { nicknameFlag ->
            if (nicknameFlag) {
                // signUpUserNickname = binding.tieEtSignupNickname.text.toString()
                binding.telSignupNickname.error = null
            } else {
                binding.telSignupNickname.error = "아이디는 영문, 숫자 포함입니다."
            }
            btnEnable()
        }
    }

    private fun btnEnable() {
        signUpViewModel.signUpBtnFlag()
        binding.btnSignupSignup.isEnabled = signUpViewModel.signUpBtnFlag.value == true
    }

    private fun clickSignUpBtn() {
        binding.btnSignupSignup.setOnClickListener {
            // val signUpUser = User(signUpUserId, signUpUserPw, signUpUserNickname)
            signUpViewModel.signUpUserApi(this)

            signUpViewModel.signUpResult.observe(this) { signUpSuccessful ->
                if (signUpSuccessful) {
                    showShortToast(getString(R.string.signup_success))
                    val intent = Intent(this, LoginActivity::class.java)
                    // intent.putExtra("signUpUser", signUpUser)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                } else {
                    showShortSnackBar(binding.root, getString(R.string.signup_fail))
                }
            }
        }
    }
}
