package com.brunoterra.hsvmaker

import androidx.annotation.FloatRange

data class HSVColor(
    @FloatRange(from = 0.0, to = 360.0) var hue: Float,
    @FloatRange(from = 0.0, to = 1.0) var saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) var value: Float,
)
