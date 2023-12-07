package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.setOnSingleClickListener
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
        binding.btnSignupSignup.setOnSingleClickListener {
            signUpViewModel.signUpUserApi()
            observeSignUpResult()
        }
    }

    private fun observeValid() {
        signUpViewModel.idFlag.flowWithLifecycle(lifecycle).onEach { idFlag ->
            binding.telSignupId.error = if (idFlag) null else getString(R.string.id_layout_title)
            btnEnable()
        }.launchIn(lifecycleScope)
        signUpViewModel.pwFlag.flowWithLifecycle(lifecycle).onEach { pwFlag ->
            binding.telSignupPw.error = if (pwFlag) null else getString(R.string.pw_layout_title)
            btnEnable()
        }.launchIn(lifecycleScope)
        signUpViewModel.nicknameFlag.flowWithLifecycle(lifecycle).onEach { nicknameFlag ->
            binding.telSignupNickname.error =
                if (nicknameFlag) null else getString(R.string.nickname_layout_title)
            btnEnable()
        }.launchIn(lifecycleScope)
    }

    private fun btnEnable() {
        signUpViewModel.signUpBtnFlag()
        binding.btnSignupSignup.isEnabled = signUpViewModel.signUpBtnFlag.value == true
        if (binding.btnSignupSignup.isEnabled) {
            signUpViewModel.onUserTextSizeChanged(40)
        } else {
            signUpViewModel.onUserTextSizeChanged(10)
        }
    }

    private fun observeSignUpResult() {
        lifecycleScope.launch {
            signUpViewModel.signUpResult.collect { uiState ->
                when (uiState) {
                    is UiState.Success -> {
                        goLogin()
                    }

                    is UiState.Failure -> {
                        showShortSnackBar(binding.root, "회원가입 실패 : ${uiState.errorMessage}")
                    }

                    is UiState.Loading -> {
                        showShortSnackBar(binding.root, getString(R.string.uistate_loading))
                    }

                    is UiState.Initial -> {
                        showShortSnackBar(binding.root, getString(R.string.uistate_loading))
                    }
                }
            }
        }
    }

    private fun goLogin() {
        showShortToast(getString(R.string.signup_success))
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
