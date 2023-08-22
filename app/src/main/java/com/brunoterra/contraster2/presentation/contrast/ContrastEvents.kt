package com.brunoterra.contraster2.presentation.contrast

sealed class ContrastEvents {
    data class HueChange(val hue: Float) : ContrastEvents()
    data class SaturationChange(val saturation: Float) : ContrastEvents()
    data class LightnessChange(val lightness: Float) : ContrastEvents()
    data class TargetChange(val target: Target) : ContrastEvents()
    object SwitchColors : ContrastEvents()

}