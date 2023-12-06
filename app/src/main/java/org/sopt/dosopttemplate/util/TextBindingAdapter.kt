package org.sopt.dosopttemplate.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextBindingAdapter {
    @BindingAdapter("dynamicTextSize")
    @JvmStatic
    fun setDynamicTextSize(textView: TextView, textSize: Float) {
        textView.textSize = textSize
    }
}
