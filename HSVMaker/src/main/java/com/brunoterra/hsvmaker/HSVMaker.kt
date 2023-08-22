package com.brunoterra.hsvmaker

import android.graphics.Color
import androidx.core.graphics.ColorUtils

object HSVMaker {

    fun createFromHSV(hsvColor: HSVColor): Int {
        val rgb = ColorUtils.HSLToColor(floatArrayOf(hsvColor.hue, hsvColor.saturation, hsvColor.value))
        return Color.rgb(Color.red(rgb), Color.green(rgb), Color.blue(rgb))
    }
}
