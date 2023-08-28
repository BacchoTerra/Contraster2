package com.brunoterra.hslmaker.utils

import androidx.compose.ui.graphics.Color

internal object HSLUtils {

    fun getHueSequentialColorList() = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta,
        Color.Red,
    )

    fun getLightnessSequentialList(mainColor: Color) = listOf(Color.Black, mainColor, Color.White)
    fun getSaturationSequentialList(mainColor: Color) = listOf(Color.Gray, mainColor)
}
