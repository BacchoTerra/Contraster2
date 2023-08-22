package com.brunoterra.contraster2.domain.usecase.contrast

import android.util.Log
import com.brunoterra.hslmaker.domain.model.HSLColor
import com.brunoterra.hslmaker.utils.HSLMaker
import com.brunoterra.hslmaker.utils.HSLRanges

private const val TAG = "HSV_CALCULATION"

class HSLChangeUseCase {
    operator fun invoke(hue: Float, saturation: Float, lightness: Float): Int {
        if (hue !in HSLRanges.HUE_RANGE || saturation !in HSLRanges.SAT_RANGE || lightness !in HSLRanges.LIGHTNESS_RANGE) {
            return -1
        }
        return HSLMaker.createFromHSL(HSLColor(hue, saturation, lightness))
    }

}