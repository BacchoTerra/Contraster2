package com.brunoterra.hsvmaker

import androidx.annotation.FloatRange

data class HSV(
    @FloatRange(from = 0.0, to = 360.0) val hue: Float,
    @FloatRange(from = 0.0, to = 1.0) val saturation: Float,
    @FloatRange(from = 0.0, to = 1.0) val value: Float,
)
