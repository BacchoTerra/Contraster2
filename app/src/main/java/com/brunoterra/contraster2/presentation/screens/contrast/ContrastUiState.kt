package com.brunoterra.contraster2.presentation.screens.contrast

import com.brunoterra.contraster2.domain.model.Contrast
import com.brunoterra.contraster2.domain.model.HSLWrapper
import com.brunoterra.contraster2.presentation.utils.Target
import com.brunoterra.hslmaker.domain.model.HSLColor

data class ContrastUiState(
    val backgroundWrapper: HSLWrapper = HSLWrapper(HSLColor(217f, .82f, .53f)),
    val foregroundWrapper: HSLWrapper = HSLWrapper(HSLColor(0f, 0f, 1f)),
    val target: Target = Target.BACKGROUND,
    val contrast: Contrast = Contrast()
)