package com.brunoterra.contraster2.presentation.contrast


private const val INITIAL_HUE = 180f
private const val INITIAL_SAT = .5f
private const val INITIAL_VALUE = .5f

data class HSVWrapper(
    val hueSlider: Float = INITIAL_HUE,
    val saturationSlider: Float = INITIAL_SAT,
    val valueSlider: Float = INITIAL_VALUE,
    val color: Int = -1,
    val colorHex: String = "0xXXXXXX",
)

data class ContrastUiState(
    val backgroundWrapper: HSVWrapper = HSVWrapper(),
    val foregroundWrapper: HSVWrapper = HSVWrapper(),
    val target: Target = Target.BACKGROUND,
) {
}

enum class Target {
    BACKGROUND,
    FOREGROUND,
}