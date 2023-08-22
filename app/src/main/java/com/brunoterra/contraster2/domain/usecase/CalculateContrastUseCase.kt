package com.brunoterra.contraster2.domain.usecase

import com.brunoterra.contraster2.presentation.contrast.Contrast

class CalculateContrastUseCase(private val calculateContrast: CalculateContrast) {
    operator fun invoke(foregroundColor: Int, backgroundColor: Int): Contrast {
        return calculateContrast(foregroundColor, backgroundColor)
    }
}