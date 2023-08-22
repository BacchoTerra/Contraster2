package com.brunoterra.contraster2.presentation.contrast

import com.brunoterra.hslmaker.domain.model.HSLColor

data class ContrastUiState(
    val backgroundWrapper: HSLWrapper = HSLWrapper(HSLColor(217f, .82f, .53f)),
    val foregroundWrapper: HSLWrapper = HSLWrapper(HSLColor(0f, 0f, 1f)),
    val target: Target = Target.BACKGROUND,
    val contrast: Contrast = Contrast()
) {
}

data class HSLWrapper(
    val hslColor: HSLColor = HSLColor(),
    val color: Int = -1,
    val colorHex: String = "0xXXXXXX",
)

data class Contrast(
    val score: Double = -1.0,
    val ratio: ContrastRatio = ContrastRatio.FAIL
) {
    companion object {
        val FAIL_RANGE = 0.0f..2.999999f
        val AA_LARGE_RANGE = 3.0f..4.499999f
        val AA = 4.5f..6.99999f
        val AAA = 7.0f..21.00f
    }
}

enum class Target {
    BACKGROUND,
    FOREGROUND,
}

enum class ContrastRatio(val value: String) {
    FAIL("Fail"),
    AALarge("AA Large"),
    AA("AA"),
    AAA("AAA"),
}