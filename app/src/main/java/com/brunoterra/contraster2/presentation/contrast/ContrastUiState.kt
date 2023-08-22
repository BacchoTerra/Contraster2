package com.brunoterra.contraster2.presentation.contrast

import com.brunoterra.hsvmaker.domain.model.HSVColor

data class ContrastUiState(
    val backgroundWrapper: HSVWrapper = HSVWrapper(HSVColor(217f, .82f, .53f)),
    val foregroundWrapper: HSVWrapper = HSVWrapper(HSVColor(0f, 0f, 1f)),
    val target: Target = Target.BACKGROUND,
) {
}

data class HSVWrapper(
    val hsvColor: HSVColor = HSVColor(),
    val color: Int = -1,
    val colorHex: String = "0xXXXXXX",
)

enum class Target {
    BACKGROUND,
    FOREGROUND,
}