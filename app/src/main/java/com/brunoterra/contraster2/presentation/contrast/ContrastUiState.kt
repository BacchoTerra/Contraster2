package com.brunoterra.contraster2.presentation.contrast

import com.brunoterra.hsvmaker.HSVColor

data class HSVWrapper(
    val hsvColor: HSVColor = HSVColor(),
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