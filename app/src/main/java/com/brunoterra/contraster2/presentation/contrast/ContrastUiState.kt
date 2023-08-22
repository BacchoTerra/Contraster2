package com.brunoterra.contraster2.presentation.contrast

import com.brunoterra.hslmaker.domain.model.HSLColor

data class ContrastUiState(
    val backgroundWrapper: HSLWrapper = HSLWrapper(HSLColor(217f, .82f, .53f)),
    val foregroundWrapper: HSLWrapper = HSLWrapper(HSLColor(0f, 0f, 1f)),
    val target: Target = Target.BACKGROUND,
) {
}

data class HSLWrapper(
    val hslColor: HSLColor = HSLColor(),
    val color: Int = -1,
    val colorHex: String = "0xXXXXXX",
)

enum class Target {
    BACKGROUND,
    FOREGROUND,
}