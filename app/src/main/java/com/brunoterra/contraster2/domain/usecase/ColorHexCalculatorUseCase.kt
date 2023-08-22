package com.brunoterra.contraster2.domain.usecase

import android.util.Log


private const val TAG = "COLOR_HEX"

class ColorHexCalculatorUseCase {

    operator fun invoke(color: Int): String {
        return try {
            java.lang.String.format("#%06X", 0xFFFFFF and color)
        } catch (e: Exception) {
            "Error".also {
                Log.i(TAG, "invoke: Error trying to calculate color ($color) hex code")
            }
        }
    }

}