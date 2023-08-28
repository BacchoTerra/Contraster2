package com.brunoterra.contraster2.domain.usecase.contrast

import androidx.core.graphics.ColorUtils

class CalculateContrastImpl : CalculateContrast {
    override fun calculate(foregroundColor: Int, backgroundColor: Int): Double {
        return ColorUtils.calculateContrast(foregroundColor, backgroundColor)
    }
}
