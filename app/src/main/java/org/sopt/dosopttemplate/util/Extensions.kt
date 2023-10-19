package org.sopt.dosopttemplate.util

import android.content.Context
import android.view.View
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

// fun hideKeyboard(view: View) {
//    val imm: InputMethodManager? = null
//    imm?.hideSoftInputFromWindow(view.windowToken, 0)
// }
