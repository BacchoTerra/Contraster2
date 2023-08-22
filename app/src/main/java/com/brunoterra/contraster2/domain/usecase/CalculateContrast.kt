package com.brunoterra.contraster2.domain.usecase

import androidx.core.graphics.ColorUtils

class CalculateContrast {

    operator fun invoke(foregroundColor: Int, backgroundColor: Int): Double {
        return ColorUtils.calculateContrast(foregroundColor, backgroundColor)
    }
}