package com.brunoterra.hslmaker.domain.model

import androidx.annotation.FloatRange

private const val INITIAL_HUE = 180f
private const val INITIAL_SAT = .5f
private const val INITIAL_LIGHTNESS = .5f


data class HSLColor(
    @FloatRange(from = 0.0, to = 360.0) var hue: Float = INITIAL_HUE,
    @FloatRange(from = 0.0, to = 1.0) var saturation: Float = INITIAL_SAT,
    @FloatRange(from = 0.0, to = 1.0) var lightness: Float = INITIAL_LIGHTNESS,
)