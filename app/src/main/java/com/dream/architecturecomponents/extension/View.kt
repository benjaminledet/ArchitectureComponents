package com.dream.architecturecomponents.extension

import android.view.View
import android.view.ViewGroup
import com.dream.architecturecomponents.R
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar

private fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    val params = layoutParams as? ViewGroup.MarginLayoutParams
    params?.setMargins(left, top, right, bottom)
    requestLayout()
}

fun View.showError(description: String) {
    val snackbar = longSnackbar(description)
    snackbar.view.setMargins(0,0,0,0)
    snackbar.view.backgroundColorResource = android.R.color.holo_red_dark
}

fun View.showAction(description: String, color: Int = R.color.colorAccent) {
    val snackbar = snackbar(description)
    snackbar.view.setMargins(0,0,0,0)
    snackbar.view.backgroundColorResource = color
}