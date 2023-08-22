package com.brunoterra.contraster2.presentation.contrast

import androidx.lifecycle.ViewModel
import com.brunoterra.contraster2.domain.usecase.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.HSVChangeUseCase
import com.brunoterra.hsvmaker.HSVColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.String

class ContrastViewModel(
    private val hsvChangeUseCase: HSVChangeUseCase,
    private val colorHexCalculatorUseCase: ColorHexCalculatorUseCase
) : ViewModel() {

    private val _contrastUiState = MutableStateFlow(ContrastUiState())
    val contrastUiState = _contrastUiState.asStateFlow()


    fun onEvent(event: ContrastEvents) {
        when (event) {
            is ContrastEvents.HueChange -> {
                updateColorState(hue = event.hue)
            }

            is ContrastEvents.SaturationChange -> {
                updateColorState(sat = event.saturation)
            }

            is ContrastEvents.ValueChange -> {
                updateColorState(value = event.value)
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
        value: Float? = null,
    ) {
        _contrastUiState.update { currentValue ->
            val target = currentValue.target

            val currentHsvForTarget =
                if (target == Target.BACKGROUND) currentValue.backgroundWrapper else currentValue.foregroundWrapper

            val newColor = hsvChangeUseCase(
                hue = hue ?: currentHsvForTarget.hsvColor.hue,
                saturation = sat ?: currentHsvForTarget.hsvColor.saturation,
                value = value ?: currentHsvForTarget.hsvColor.value
            )

            val hexColor = colorHexCalculatorUseCase(newColor)

            val backgroundWrapper =
                if (target == Target.BACKGROUND) currentValue.backgroundWrapper.copy(
                    hsvColor = HSVColor(
                        hue ?: currentHsvForTarget.hsvColor.hue,
                        sat ?: currentHsvForTarget.hsvColor.saturation,
                        value ?: currentHsvForTarget.hsvColor.value,
                    ),
                    color = newColor,
                    colorHex = hexColor,
                ) else currentValue.backgroundWrapper

            val foregroundWrapper =
                if (target == Target.FOREGROUND) currentValue.foregroundWrapper.copy(
                    hsvColor = HSVColor(
                        hue ?: currentHsvForTarget.hsvColor.hue,
                        sat ?: currentHsvForTarget.hsvColor.saturation,
                        value ?: currentHsvForTarget.hsvColor.value,
                    ),
                    color = newColor,
                    colorHex = hexColor,
                ) else currentValue.foregroundWrapper

            currentValue.copy(
                backgroundWrapper = backgroundWrapper,
                foregroundWrapper = foregroundWrapper,
                target = target
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