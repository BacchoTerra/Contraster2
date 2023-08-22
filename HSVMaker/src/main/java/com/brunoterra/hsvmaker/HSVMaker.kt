package com.brunoterra.hsvmaker

import android.graphics.Color
import androidx.core.graphics.ColorUtils

class HSVMaker {

    fun createFromHSV(hsv: HSV, alpha: Int): Int {
        val rgb = ColorUtils.HSLToColor(floatArrayOf(hsv.hue, hsv.saturation, hsv.value))
        return Color.argb(alpha, Color.red(rgb), Color.green(rgb), Color.blue(rgb))
    }
}
