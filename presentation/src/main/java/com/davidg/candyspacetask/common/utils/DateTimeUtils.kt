package com.davidg.candyspacetask.common.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {

    companion object{

        fun getDate(milliSeconds: Long): String? {
        val epochString = milliSeconds
        val epoch = epochString
        val date = Date(epoch * 1000)
        return date.toGMTString()
    }
    }
}