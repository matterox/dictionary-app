package com.matterox.dictionary.extentions

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.google.android.material.snackbar.Snackbar
import com.matterox.dictionary.R

fun View.showSnackBar(message: String?) {
    showSnackBarWithImage(message, 0)
}

fun View.showErrorSnackBar(message: String?) {
    showSnackBarWithImage(message, R.drawable.ic_error_red_outline_24)
}

fun View.showSnackBarWithImage(message: String?, @DrawableRes drawableRes: Int) {
    if (!message.isNullOrEmpty()) {
        val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundResource(R.drawable.bg_snackbar)
        val textView =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.compoundDrawablePadding =
            resources.getDimensionPixelSize(R.dimen.snack_bar_drawable_padding)
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0)
        textView.gravity = Gravity.CENTER_VERTICAL
        snackBar.show()
    }
}

fun View.setOnDebouncedClickListener(action: () -> Unit) {
    val actionDebouncer = ActionDebouncer(action)

    setOnClickListener {
        actionDebouncer.notifyAction()
    }
}

private class ActionDebouncer(private val action: () -> Unit) {

    companion object {
        const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
    }

    private var lastActionTime = 0L

    fun notifyAction() {
        val now = SystemClock.elapsedRealtime()

        val millisecondsPassed = now - lastActionTime
        val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
        lastActionTime = now

        if (actionAllowed) {
            action.invoke()
        }
    }
}

fun View.hideKeyboard(activity: Activity? = null) {
    activity?.window?.setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
    )
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}