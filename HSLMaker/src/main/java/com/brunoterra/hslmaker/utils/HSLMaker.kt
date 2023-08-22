package com.brunoterra.hslmaker.utils

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import com.brunoterra.hslmaker.domain.model.HSLColor

object HSLMaker {

    fun createFromHSV(hslColor: HSLColor): Int {
        val rgb = ColorUtils.HSLToColor(floatArrayOf(hslColor.hue, hslColor.saturation, hslColor.lightness))
        return Color.rgb(Color.red(rgb), Color.green(rgb), Color.blue(rgb))
    }
}
