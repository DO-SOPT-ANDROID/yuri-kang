package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.showShortSnackBar
import org.sopt.dosopttemplate.util.showShortToast
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    companion object {
        private const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$"
        private const val PW_PATTERN =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$"
    }

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // do nothing
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val signUpUserId = binding.tieEtSignupId.text.toString()
        val signUpUserPw = binding.tieEtSignupPw.text.toString()
        val signUpUserNickname = binding.tieEtSignupNickname.text.toString()
        val signUpUser = User(signUpUserId, signUpUserPw, signUpUserNickname)

        binding.btnSignupSignup.setOnClickListener {
            signUpViewModel.signUpUserApi(signUpUser, this)

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
