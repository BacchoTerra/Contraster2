package com.brunoterra.contraster2.domain.usecase

import androidx.core.graphics.ColorUtils
import com.brunoterra.contraster2.presentation.contrast.Contrast
import com.brunoterra.contraster2.presentation.contrast.ContrastRatio

class CalculateContrast {

    operator fun invoke(foregroundColor: Int, backgroundColor: Int): Contrast {
        val score = ColorUtils.calculateContrast(foregroundColor, backgroundColor)
        val ratio = when (score) {
            in Contrast.FAIL_RANGE -> ContrastRatio.FAIL
            in Contrast.AA_LARGE_RANGE -> ContrastRatio.AALarge
            in Contrast.AA -> ContrastRatio.AA
            else -> ContrastRatio.AAA
        }
        return Contrast(score, ratio)
    }
}