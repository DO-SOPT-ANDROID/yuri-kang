package org.sopt.dosopttemplate.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showShortToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT,
    ).show()
}

fun showShortSnackBar(view: View, text: String) {
    Snackbar.make(
        view,
        text,
        Snackbar.LENGTH_SHORT,
    ).show()
}

fun showShortSnackBarAction(view: View, text: String, actionText: String) {
    Snackbar.make(
        view,
        text,
        Snackbar.LENGTH_SHORT,
    ).setAction(actionText) {
        // 액션 클릭 시 이벤트 작성
    }.show()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
