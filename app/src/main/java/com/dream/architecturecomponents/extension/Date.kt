package com.dream.architecturecomponents.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(): String {
    val formatter = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())
    return "le ${formatter.format(this)}"
}