package com.brunoterra.contraster2.presentation.contrast

import com.brunoterra.hsvmaker.HSVColor

sealed class ContrastEvents {
    data class HueChange(val hue: Float) : ContrastEvents()
    data class SaturationChange(val saturation: Float) : ContrastEvents()
    data class ValueChange(val value: Float) : ContrastEvents()
    data class TargetChange(val target: Target) : ContrastEvents()

}