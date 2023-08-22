package com.brunoterra.contraster2.domain.model

import com.brunoterra.hslmaker.domain.model.HSLColor

data class HSLWrapper(
    val hslColor: HSLColor = HSLColor(),
    val color: Int = -1,
    val colorHex: String = "0xXXXXXX",
)