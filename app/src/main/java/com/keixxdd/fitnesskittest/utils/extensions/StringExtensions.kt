package com.keixxdd.fitnesskittest.utils.extensions

import java.text.DateFormatSymbols

    fun createEmptyString() = ""

    fun String.beautyDate() : String {
        val dateList = this.split('-')
        val month = DateFormatSymbols().months[dateList[1].toInt()-1]
        val sb = StringBuilder()
        sb.append(dateList[2].toInt())
            .append(" ")
            .append(month)
        return sb.toString()
    }
