package com.brunoterra.contraster2.domain.model

import com.brunoterra.contraster2.domain.utils.ContrastRatio

data class Contrast(
    val score: Double = -1.0,
    val ratio: ContrastRatio = ContrastRatio.FAIL
) {
    companion object {
        val FAIL_RANGE = 0.0f..2.99f
        val AA_LARGE_RANGE = 3.0f..4.49f
        val AA = 4.5f..6.99f
        val AAA = 7.0f..21.00f
    }
}