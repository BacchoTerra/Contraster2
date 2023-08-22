package com.brunoterra.contraster2.presentation.contrast

import androidx.lifecycle.ViewModel
import com.brunoterra.contraster2.domain.usecase.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.HSLChangeUseCase
import com.brunoterra.hslmaker.domain.model.HSLColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContrastViewModel(
    private val hslChangeUseCase: HSLChangeUseCase,
    private val colorHexCalculatorUseCase: ColorHexCalculatorUseCase
) : ViewModel() {

    private val _contrastUiState = MutableStateFlow(ContrastUiState())
    val contrastUiState = _contrastUiState.asStateFlow()

    init {
        updateColorState(target = Target.BACKGROUND)
        updateColorState(target = Target.FOREGROUND)
    }


    fun onEvent(event: ContrastEvents) {
        when (event) {
            is ContrastEvents.HueChange -> {
                updateColorState(hue = event.hue)
            }

            is ContrastEvents.SaturationChange -> {
                updateColorState(sat = event.saturation)
            }

            is ContrastEvents.LightnessChange -> {
                updateColorState(lightness = event.lightness)
            }

            is ContrastEvents.TargetChange -> {
                _contrastUiState.update { currentValue ->
                    currentValue.copy(target = event.target)
                }
            }

            is ContrastEvents.SwitchColors -> {
                switchColors()
            }
        }
    }

    private fun updateColorState(
        hue: Float? = null,
        sat: Float? = null,
        lightness: Float? = null,
        target: Target? = null
    ) {
        _contrastUiState.update { currentValue ->
            val mTarget = target ?: currentValue.target

            val currentHsvForTarget =
                if (mTarget == Target.BACKGROUND) currentValue.backgroundWrapper else currentValue.foregroundWrapper

            val newColor = hslChangeUseCase(
                hue = hue ?: currentHsvForTarget.hslColor.hue,
                saturation = sat ?: currentHsvForTarget.hslColor.saturation,
                lightness = lightness ?: currentHsvForTarget.hslColor.lightness
            )

            val hexColor = colorHexCalculatorUseCase(newColor)

            val updatedHSVWrapper =
                (if (mTarget == Target.BACKGROUND) currentValue.backgroundWrapper else currentValue.foregroundWrapper).copy(
                    hslColor = HSLColor(
                        hue ?: currentHsvForTarget.hslColor.hue,
                        sat ?: currentHsvForTarget.hslColor.saturation,
                        lightness ?: currentHsvForTarget.hslColor.lightness,
                    ),
                    color = newColor,
                    colorHex = hexColor,
                )

            currentValue.copy(
                backgroundWrapper = if (mTarget == Target.BACKGROUND) updatedHSVWrapper else currentValue.backgroundWrapper,
                foregroundWrapper = if (mTarget == Target.FOREGROUND) updatedHSVWrapper else currentValue.foregroundWrapper,
            )
        }
    }

    private fun switchColors() {
        _contrastUiState.update { currentValue ->
            currentValue.copy(
                backgroundWrapper = currentValue.foregroundWrapper,
                foregroundWrapper = currentValue.backgroundWrapper
            )
        }
    }
}