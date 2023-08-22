package com.brunoterra.hsvmaker.domain.model

import androidx.annotation.FloatRange

private const val INITIAL_HUE = 180f
private const val INITIAL_SAT = .5f
private const val INITIAL_VALUE = .5f


data class HSVColor(
    @FloatRange(from = 0.0, to = 360.0) var hue: Float = INITIAL_HUE,
    @FloatRange(from = 0.0, to = 1.0) var saturation: Float = INITIAL_SAT,
    @FloatRange(from = 0.0, to = 1.0) var value: Float = INITIAL_VALUE,
)