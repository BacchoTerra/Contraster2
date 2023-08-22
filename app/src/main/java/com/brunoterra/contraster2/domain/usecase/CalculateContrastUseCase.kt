package com.brunoterra.contraster2.domain.usecase

import com.brunoterra.contraster2.domain.model.Contrast
import com.brunoterra.contraster2.domain.utils.ContrastRatio
import java.math.BigDecimal
import java.math.RoundingMode

class CalculateContrastUseCase(private val calculateContrast: CalculateContrast) {
    operator fun invoke(foregroundColor: Int, backgroundColor: Int): Contrast {

        val score = BigDecimal(calculateContrast(foregroundColor, backgroundColor)).setScale(
            2,
            RoundingMode.HALF_EVEN
        ).toDouble()

        val ratio = when (score) {
            in Contrast.FAIL_RANGE -> ContrastRatio.FAIL
            in Contrast.AA_LARGE_RANGE -> ContrastRatio.AALarge
            in Contrast.AA -> ContrastRatio.AA
            else -> ContrastRatio.AAA
        }

        return Contrast(score, ratio)
    }
}