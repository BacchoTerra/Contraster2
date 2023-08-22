package com.brunoterra.hsvmaker

import android.graphics.Color
import androidx.core.graphics.ColorUtils

object HSVMaker {

    fun createFromHSV(hsv: HSV): Int {
        val rgb = ColorUtils.HSLToColor(floatArrayOf(hsv.hue, hsv.saturation, hsv.value))
        return Color.rgb(Color.red(rgb), Color.green(rgb), Color.blue(rgb))
    }
}
