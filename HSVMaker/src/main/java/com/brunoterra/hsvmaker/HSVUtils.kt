package com.brunoterra.hsvmaker

import androidx.compose.ui.graphics.Color

internal object HSVUtils {

    val hsvSequentialColors = listOf(
        Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue,
        Color.Magenta, Color.Red
    )

    val hueFloatRange = 0f..360f
    val valueFloatRange = 0f..1f

    fun getValueSequentialList(mainColor: Color) = listOf(Color.Black, mainColor, Color.White)
    fun getSaturationSequentialList(mainColor: Color) = listOf(Color.Gray, mainColor)
}