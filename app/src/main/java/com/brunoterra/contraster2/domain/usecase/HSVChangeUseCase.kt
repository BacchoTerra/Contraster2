package com.brunoterra.contraster2.domain.usecase

import com.brunoterra.hsvmaker.HSVColor
import com.brunoterra.hsvmaker.HSVMaker

class HSVChangeUseCase {
    operator fun invoke(hue: Float, saturation: Float, value: Float): Int {
        return HSVMaker.createFromHSV(HSVColor(hue, saturation, value))
    }

}