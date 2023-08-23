package com.brunoterra.contraster2.presentation.screens.contrast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import com.brunoterra.contraster2.presentation.utils.Target

@Composable
fun ContrastScreen(
    contrastVM: ContrastViewModel = koinViewModel(),
    onTestPalette: (backgroundColor: Int, foregroundColor: Int, score: Double) -> Unit
) {

    val state = contrastVM.contrastUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(state.value.backgroundWrapper.color))
            .verticalScroll(rememberScrollState())
            .height(IntrinsicSize.Max)
    ) {

        ContrastSection(
            state.value.foregroundWrapper.color,
            state.value.contrast,
            onSwitch = {
                contrastVM.onEvent(ContrastEvents.SwitchColors)
            },
            onTestPalette = {
                onTestPalette(
                    state.value.backgroundWrapper.color,
                    state.value.foregroundWrapper.color,
                    state.value.contrast.score
                )
            }
        )

        Column(
            Modifier
                .fillMaxSize()
                .background(
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                    color = MaterialTheme.colorScheme.background
                )
                .padding(16.dp)
        ) {
            val hexCode =
                if (state.value.target == Target.BACKGROUND) state.value.backgroundWrapper.colorHex else state.value.foregroundWrapper.colorHex
            ColorHeaderSection(state.value.target, hexCode) {
                contrastVM.onEvent(ContrastEvents.TargetChange(it))
            }
            Spacer(modifier = Modifier.height(24.dp))
            val sliderValues =
                if (state.value.target == Target.BACKGROUND) state.value.backgroundWrapper else state.value.foregroundWrapper
            SlidersSection(
                hue = sliderValues.hslColor.hue,
                sat = sliderValues.hslColor.saturation,
                lightness = sliderValues.hslColor.lightness,
                onHueChange = {
                    contrastVM.onEvent(ContrastEvents.HueChange(it))
                },
                onSaturationChange = {
                    contrastVM.onEvent(ContrastEvents.SaturationChange(it))
                },
                onLightnessChange = {
                    contrastVM.onEvent(ContrastEvents.LightnessChange(it))
                })
        }
    }
}