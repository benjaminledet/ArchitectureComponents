package com.dream.architecturecomponents.extension

import android.app.ActivityOptions
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.dream.architecturecomponents.R

fun FragmentActivity.startAnimatedActivity(intent: Intent) {
    startActivity(intent, ActivityOptions.makeCustomAnimation(this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle())
}