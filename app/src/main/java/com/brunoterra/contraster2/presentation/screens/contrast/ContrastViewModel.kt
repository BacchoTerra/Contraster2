package com.brunoterra.contraster2.presentation.screens.contrast

import androidx.lifecycle.ViewModel
import com.brunoterra.contraster2.domain.model.HSLWrapper
import com.brunoterra.contraster2.domain.usecase.CalculateContrastUseCase
import com.brunoterra.contraster2.domain.usecase.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.HSLChangeUseCase
import com.brunoterra.hslmaker.domain.model.HSLColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.brunoterra.contraster2.presentation.utils.Target

class ContrastViewModel(
    private val hslChangeUseCase: HSLChangeUseCase,
    private val colorHexCalculatorUseCase: ColorHexCalculatorUseCase,
    private val calculateContrastUseCase: CalculateContrastUseCase,
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

            var backgroundWrapper = currentValue.backgroundWrapper
            var foregroundWrapper = currentValue.foregroundWrapper

            if (mTarget == Target.BACKGROUND) {
                backgroundWrapper = generateHSLWrapper(Target.BACKGROUND, hue, sat, lightness)
            }

            if (mTarget == Target.FOREGROUND) {
                foregroundWrapper = generateHSLWrapper(Target.FOREGROUND, hue, sat, lightness)
            }

            val contrast =
                calculateContrastUseCase(foregroundWrapper.color, backgroundWrapper.color)

            currentValue.copy(
                backgroundWrapper = backgroundWrapper,
                foregroundWrapper = foregroundWrapper,
                contrast = contrast
            )
        }
    }

    private fun generateHSLWrapper(
        target: Target,
        hue: Float? = null,
        sat: Float? = null,
        lightness: Float? = null,
    ): HSLWrapper {
        val currentWrapperForTarget =
            if (target == Target.BACKGROUND) _contrastUiState.value.backgroundWrapper else _contrastUiState.value.foregroundWrapper

        val newColor = hslChangeUseCase(
            hue = hue ?: currentWrapperForTarget.hslColor.hue,
            saturation = sat ?: currentWrapperForTarget.hslColor.saturation,
            lightness = lightness ?: currentWrapperForTarget.hslColor.lightness
        )

        val hexColor = colorHexCalculatorUseCase(newColor)

        return HSLWrapper(
            hslColor = HSLColor(
                hue ?: currentWrapperForTarget.hslColor.hue,
                sat ?: currentWrapperForTarget.hslColor.saturation,
                lightness ?: currentWrapperForTarget.hslColor.lightness,
            ),
            color = newColor,
            colorHex = hexColor,
        )
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