package com.test.healthapplication.util

object NumberTranslator {
    fun toPersian(c: String): String {
        var c = c
        val enN =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val faN =
            arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        for (i in 0..9) {
            c = c.replace(enN[i], faN[i])
        }
        return c
    }

    fun toEnglish(c: String): String {
        var c = c
        val enN =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        val faN =
            arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        for (i in 0..9) {
            c = c.replace(faN[i], enN[i])
        }
        return c
    }
}
