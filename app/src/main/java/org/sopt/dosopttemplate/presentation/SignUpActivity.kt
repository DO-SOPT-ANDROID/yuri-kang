package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.util.BackPressedUtil

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var imm: InputMethodManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignupSignup.setOnClickListener {
            val userId = binding.etSignupId.text.toString()
            val userPw = binding.etSignupPw.text.toString()
            val userNickname = binding.etSignupNickname.text.toString()
            val userAge = binding.etSignupAge.text.toString()

            // 필수 조건 미입력 시
            if (userId.isEmpty() || userPw.isEmpty() || userNickname.isEmpty() || userAge.isEmpty()) {
                setSnackbar(getString(R.string.signup_fail))
            } else {
                // 필수 조건 모두 입력 시
                // 조건
                if (userId.length > 10 || userId.length < 6) {
                    setSnackbar(getString(R.string.signup_id))
                } else if (userPw.length > 12 || userPw.length < 8) {
                    setSnackbar(getString(R.string.signup_pw))
                } else if (userNickname.isBlank()) {
                    setSnackbar(getString(R.string.signup_nickname))
                } else if (userAge.length >= 3 || userAge == "0") {
                    setSnackbar(getString(R.string.signup_age))
                } else {
                    // 화면 전환
                    val toast = Toast.makeText(
                        applicationContext,
                        getString(R.string.signup_success),
                        Toast.LENGTH_SHORT,
                    )
                    toast.show()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("ID", userId)
                    intent.putExtra("PW", userPw)
                    intent.putExtra("Nickname", userNickname)
                    intent.putExtra("Age", userAge)
                    startActivity(intent)
                    finish()
                }
            }
        }
        // 키보드 InputMethodManager 세팅
        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?

        val backPressedUtil = BackPressedUtil(this)
        backPressedUtil.BackButton()
    }

    private fun setSnackbar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    fun hideKeyboard(v: View) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
