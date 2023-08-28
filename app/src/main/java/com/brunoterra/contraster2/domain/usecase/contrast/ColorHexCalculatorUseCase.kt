package com.brunoterra.contraster2.domain.usecase.contrast

class ColorHexCalculatorUseCase {

    operator fun invoke(color: Int): String {
        return try {
            java.lang.String.format("#%06X", 0xFFFFFF and color)
        } catch (e: Exception) {
            "Error"
        }
    }
}
