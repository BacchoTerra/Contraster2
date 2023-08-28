package com.brunoterra.contraster2.domain.usecase.contrast

interface CalculateContrast {
    fun calculate(foregroundColor: Int, backgroundColor: Int): Double
}
