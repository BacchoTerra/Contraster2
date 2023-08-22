package com.brunoterra.hsvmaker.utils

import android.graphics.Color
import androidx.core.graphics.ColorUtils
import com.brunoterra.hsvmaker.domain.model.HSVColor

object HSVMaker {

    fun createFromHSV(hsvColor: HSVColor): Int {
        val rgb = ColorUtils.HSLToColor(floatArrayOf(hsvColor.hue, hsvColor.saturation, hsvColor.value))
        return Color.rgb(Color.red(rgb), Color.green(rgb), Color.blue(rgb))
    }
}
