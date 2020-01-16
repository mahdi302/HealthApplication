package com.test.healthapplication.model

import com.fanhl.flamebarchart.TravelChart
import com.test.healthapplication.util.SpanUtils
import kotlin.math.abs

data class Item(val x: Int, val y: Float?) : TravelChart.IItem {
    override fun getXLabel(): CharSequence {
        return if (abs(x - 15) <= 0.01f) "Today" else "$x"
    }

    override fun getXHint(): CharSequence {
//        return "${(x * y * 100).toInt()}km"
        return SpanUtils()
            .append("${(x * (y ?: 0f) * 100).toInt()}")
            .append(" ")
//                .append("km")
            .append("km").setFontSize(11)
            .create()
    }

    override fun getYAxis(): Float? {
        return y
    }

}