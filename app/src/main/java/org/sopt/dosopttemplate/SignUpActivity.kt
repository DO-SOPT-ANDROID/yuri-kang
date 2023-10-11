package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
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
                setSnackbar("가입을 위해 필요한 조건을 모두 입력해주세요.")
            } else {
                // 필수 조건 모두 입력 시
                // 조건
                if (userId.length > 10 || userId.length < 6) {
                    setSnackbar("ID는 6글자 이상 10글자 이하로 설정 가능합니다.")
                } else if (userPw.length > 12 || userPw.length < 8) {
                    setSnackbar("PW는 8글자 이상 12글자 이하로 설정 가능합니다.")
                } else if (userNickname.isBlank()) {
                    setSnackbar("닉네임을 입력해주세요. 공백은 불가능합니다.")
                } else if (userAge.length >= 3 || userAge == "0") {
                    setSnackbar("나이를 다시 입력해주세요.")
                } else {
                    // 화면 전환
                    val toast = Toast.makeText(applicationContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT)
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
    }

    private fun setSnackbar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT,
        ).show()
    }
}
