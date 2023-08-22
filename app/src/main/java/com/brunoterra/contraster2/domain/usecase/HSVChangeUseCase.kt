package com.brunoterra.contraster2.domain.usecase

import android.util.Log
import com.brunoterra.hsvmaker.HSVColor
import com.brunoterra.hsvmaker.HSVMaker
import com.brunoterra.hsvmaker.ui.HSVRanges

private const val TAG = "HSV_CALCULATION"

class HSVChangeUseCase {
    operator fun invoke(hue: Float, saturation: Float, value: Float): Int {
        if (hue !in HSVRanges.HUE_RANGE || saturation !in HSVRanges.SAT_RANGE || value !in HSVRanges.SAT_RANGE) {
            Log.i(
                TAG,
                "invoke: Error creating HSV with values: hue($hue)/ saturation($saturation)/ value($value)"
            )
            return -1
        }
        return HSVMaker.createFromHSV(HSVColor(hue, saturation, value))
    }

}