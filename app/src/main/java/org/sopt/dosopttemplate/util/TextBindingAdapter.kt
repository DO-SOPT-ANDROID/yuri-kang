package org.sopt.dosopttemplate.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextBindingAdapter {
    @JvmStatic
    @BindingAdapter("text_ending_word")
    fun setText(view: TextView, text: String) {
        view.hint = text + "입력하세요."
    }
}
