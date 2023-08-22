package com.brunoterra.contraster2.presentation.contrast

import androidx.lifecycle.ViewModel
import com.brunoterra.contraster2.domain.usecase.HSVChangeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ContrastViewModel(private val hsvChangeUseCase: HSVChangeUseCase) : ViewModel() {

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
                hue = hue ?: currentHsvForTarget.hueSlider,
                saturation = sat ?: currentHsvForTarget.saturationSlider,
                value = value ?: currentHsvForTarget.valueSlider
            )

            val backgroundWrapper =
                if (target == Target.BACKGROUND) currentValue.backgroundWrapper.copy(
                    hueSlider = hue ?: currentHsvForTarget.hueSlider,
                    saturationSlider = sat ?: currentHsvForTarget.saturationSlider,
                    valueSlider = value ?: currentHsvForTarget.valueSlider,
                    color = newColor
                ) else currentValue.backgroundWrapper

            val foregroundWrapper =
                if (target == Target.FOREGROUND) currentValue.foregroundWrapper.copy(
                    hueSlider = hue ?: currentHsvForTarget.hueSlider,
                    saturationSlider = sat ?: currentHsvForTarget.saturationSlider,
                    valueSlider = value ?: currentHsvForTarget.valueSlider,
                    color = newColor
                ) else currentValue.foregroundWrapper

            currentValue.copy(
                backgroundWrapper = backgroundWrapper,
                foregroundWrapper = foregroundWrapper,
                target = target
            )
        }
    }
}