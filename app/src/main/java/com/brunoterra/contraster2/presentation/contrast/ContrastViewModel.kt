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
                updateUiState(hue = event.hue)
            }

            is ContrastEvents.SaturationChange -> {
                updateUiState(sat = event.saturation)
            }

            is ContrastEvents.ValueChange -> {
                updateUiState(value = event.value)
            }
        }
    }

    private fun updateUiState(
        hue: Float = _contrastUiState.value.hueSlider,
        sat: Float = _contrastUiState.value.saturationSlider,
        value: Float = _contrastUiState.value.valueSlider,
    ) {
        _contrastUiState.update { currentValue ->
            val color = hsvChangeUseCase(
                hue = hue,
                saturation = sat,
                value = value
            )
            currentValue.copy(
                hueSlider = hue,
                saturationSlider = sat,
                valueSlider = value,
                backgroundColor = color
            )
        }
    }
}