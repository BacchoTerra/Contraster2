package com.brunoterra.contraster2.presentation.contrast


private const val INITIAL_HUE = 180f
private const val INITIAL_SAT = .5f
private const val INITIAL_VALUE = .5f

data class ContrastUiState(
    val hueSlider: Float = INITIAL_HUE,
    val saturationSlider: Float = INITIAL_SAT,
    val valueSlider: Float = INITIAL_VALUE,
    val backgroundColor : Int = -1,
    val foregroundColor : Int = -1,
    val colorHex: String = "0xXXXXXX",
) {
}