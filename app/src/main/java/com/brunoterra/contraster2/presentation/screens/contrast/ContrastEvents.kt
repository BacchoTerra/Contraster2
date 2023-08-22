package com.brunoterra.contraster2.presentation.screens.contrast

import com.brunoterra.contraster2.presentation.utils.Target

sealed class ContrastEvents {
    data class HueChange(val hue: Float) : ContrastEvents()
    data class SaturationChange(val saturation: Float) : ContrastEvents()
    data class LightnessChange(val lightness: Float) : ContrastEvents()
    data class TargetChange(val target: Target) : ContrastEvents()
    object SwitchColors : ContrastEvents()

}