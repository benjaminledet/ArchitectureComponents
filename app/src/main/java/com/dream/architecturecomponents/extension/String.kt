package com.dream.architecturecomponents.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date? =
    try { SimpleDateFormat(pattern, Locale.getDefault()).parse(this) }
    catch (e: ParseException) { null }