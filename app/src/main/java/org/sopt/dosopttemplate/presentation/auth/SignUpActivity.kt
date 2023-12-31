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
                binding.telSignupId.error = null
            } else {
                binding.telSignupId.error = getString(R.string.id_layout_title)
            }
            btnEnable()
        }
        signUpViewModel.pwFlag.observe(this) { pwFlag ->
            if (pwFlag) {
                binding.telSignupPw.error = null
            } else {
                binding.telSignupPw.error = getString(R.string.pw_layout_title)
            }
            btnEnable()
        }
        signUpViewModel.nicknameFlag.observe(this) { nicknameFlag ->
            if (nicknameFlag) {
                binding.telSignupNickname.error = null
            } else {
                binding.telSignupNickname.error = getString(R.string.nickname_layout_title)
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
