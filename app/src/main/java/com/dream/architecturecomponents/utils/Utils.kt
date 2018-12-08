package com.dream.architecturecomponents.utils

import com.dream.architecturecomponents.extension.dateToString
import java.util.*

object Utils {

    @JvmStatic fun dateToString(date: Date?): String = date?.dateToString() ?: ""

    @JvmStatic fun dateToStringCapitalize(date: Date?): String = dateToString(date).capitalize()
}