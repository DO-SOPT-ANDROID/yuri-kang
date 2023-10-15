package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.di.UserSharedPreferences

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var imm: InputMethodManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID")
        val getPw = intent.getStringExtra("PW")
        val getNickname = intent.getStringExtra("Nickname")
        val getAge = intent.getStringExtra("Age")

        // 자동 로그인이 된 경우
        if (UserSharedPreferences.getUserID(this).isBlank() ||
            UserSharedPreferences.getUserPw(this).isBlank()
        ) {
            Snackbar.make(
                binding.root,
                "회원가입을 진행해주세요.",
                Snackbar.LENGTH_SHORT,
            ).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 회원가입 하러 가기
        binding.btnSignupSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 로그인 하기
        binding.btnLoginLogin.setOnClickListener {
            if (binding.etSignupId.text.toString() == getId && binding.etSignupPw.text.toString() == getPw) {
                val toast = Toast.makeText(
                    applicationContext,
                    getString(R.string.login_success),
                    Toast.LENGTH_SHORT,
                )
                toast.show()

                // 자동 로그인
                if (binding.cbLoginAutologin.isChecked) {
                    // 유저 정보 저장
                    UserSharedPreferences.setUserID(this@LoginActivity, getId)
                    UserSharedPreferences.setUserPw(this@LoginActivity, getPw)
                    UserSharedPreferences.setUserNickname(this@LoginActivity, getNickname!!)
                    UserSharedPreferences.setUserAge(this@LoginActivity, getAge!!)
                }
            } else {
                Snackbar.make(
                    binding.root,
                    getString(R.string.login_fail),
                    Snackbar.LENGTH_SHORT,
                ).show()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        // 키보드 InputMethodManager 세팅
        imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    }

    fun hideKeyboard(v: View) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
