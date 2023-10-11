package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 회원가입 하러 가기
        binding.btnSignupSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // finish()
        }

        val getId = intent.getStringExtra("ID")
        val getPw = intent.getStringExtra("PW")
        val getNickname = intent.getStringExtra("Nickname")
        val getAge = intent.getStringExtra("Age")
        // Log.d("받은 값 확인", "$getId $getPw $getNickname $getAge")

        // 로그인 하기
        binding.btnLoginLogin.setOnClickListener {
            if (binding.etSignupId.text.toString() == getId && binding.etSignupPw.text.toString() == getPw) {
                val toast = Toast.makeText(applicationContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT)
                toast.show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("ID", getId)
                intent.putExtra("PW", getPw)
                intent.putExtra("Nickname", getNickname)
                intent.putExtra("Age", getAge)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디와 비밀번호를 다시 확인해주세요.",
                    Snackbar.LENGTH_SHORT,
                ).show()
            }
        }
    }
}
