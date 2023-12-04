package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

    private var idFlag = false
    private var pwFlag = false
    private var nicknameFlag = false

    private var signUpUserId: String = ""
    private var signUpUserPw: String = ""
    private var signUpUserNickname: String = ""

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
    }

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.telSignupId.error = "아이디를 입력해주세요."
                        idFlag = false
                    }

                    !(s.toString().matches(ID_PATTERN.toRegex())) -> {
                        binding.telSignupId.error = "아이디 양식이 일치하지 않습니다."
                        idFlag = false
                    }

                    else -> {
                        binding.telSignupId.error = null
                        signUpUserId = s.toString()
                        idFlag = true
                    }
                }
                buttonEnabled()
            }
        }
    }
    private val pwListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.telSignupPw.error = "비밀번호를 입력해주세요."
                        pwFlag = false
                    }

                    !(s.toString().matches(PW_PATTERN.toRegex())) -> {
                        binding.telSignupPw.error = "비밀번호 양식이 일치하지 않습니다."
                        pwFlag = false
                    }

                    else -> {
                        binding.telSignupPw.error = null
                        signUpUserPw = s.toString()
                        pwFlag = true
                    }
                }
                buttonEnabled()
            }
        }
    }
    private val nicknameListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.telSignupNickname.error = "닉네임을 입력해주세요."
                        nicknameFlag = false
                    }

                    else -> {
                        binding.telSignupPw.error = null
                        signUpUserNickname = s.toString()
                        nicknameFlag = true
                    }
                }
                buttonEnabled()
            }
        }
    }

    private fun buttonEnabled() {
        binding.btnSignupSignup.isEnabled = idFlag && pwFlag && nicknameFlag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignupSignup.isEnabled = false

        binding.telSignupId.editText?.addTextChangedListener(idListener)
        binding.telSignupPw.editText?.addTextChangedListener(pwListener)
        binding.telSignupNickname.editText?.addTextChangedListener(nicknameListener)

        binding.btnSignupSignup.setOnClickListener {
            val signUpUser = User(signUpUserId, signUpUserPw, signUpUserNickname)
            signUpViewModel.signUpUserApi(signUpUser, this)

            signUpViewModel.signUpResult.observe(this) { signUpSuccessful ->
                if (signUpSuccessful) {
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
