package com.brunoterra.hslmaker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brunoterra.hslmaker.domain.model.HSLColor
import com.brunoterra.hslmaker.utils.HSLMaker
import com.brunoterra.hslmaker.utils.HSLRanges
import com.brunoterra.hslmaker.utils.HSLUtils

/**
 * Specific slider for <strong>hue</strong> value of an HSL color.
 * Ranges from 0f to 360f.
 *
 * @param value: slider value
 * @param onValueChange: slide callback.
 */
@Composable
fun HueSlider(value: Float, onValueChange: (Float) -> Unit) {
    SliderContainer(
        sliderBrush = Brush.linearGradient(
            HSLUtils.getHueSequentialColorList(),
        ),
    ) {
        Slider(
            value = value,
            valueRange = HSLRanges.HUE_RANGE,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
                thumbColor = Color.Black,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,

            ),

            onValueChange = { onValueChange(it) },
        )
    }
}

/**
 * Specific slider for <strong>value (lightness)</strong> value of an HSL color.
 * Ranges from 0f to 1f.
 *
 * @param hue: the hue on which this value is being applied
 * @param lightness: slider value
 * @param onValueChange: slide callback.
 */
@Composable
fun LightnessSlider(hue: Float, lightness: Float, onValueChange: (Float) -> Unit) {
    val sliderMainColor =
        HSLUtils.getLightnessSequentialList(Color(HSLMaker.createFromHSL(HSLColor(hue, .5f, .5f))))

    SliderContainer(
        sliderBrush = Brush.linearGradient(
            sliderMainColor,
        ),
    ) {
        Slider(
            value = lightness,
            valueRange = HSLRanges.LIGHTNESS_RANGE,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
                thumbColor = Color.Black,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,

            ),

            onValueChange = { onValueChange(it) },
        )
    }
}

/**
 * Specific slider for <strong>saturation</strong> value of an HSL color.
 * Ranges from 0f to 1f.
 *
 * @param hue: the hue on which this lightness is being applied
 * @param value: slider value
 * @param onValueChange: slide callback.
 */
@Composable
fun SaturationSlider(hue: Float, value: Float, onValueChange: (Float) -> Unit) {
    val sliderMainColor =
        HSLUtils.getSaturationSequentialList(Color(HSLMaker.createFromHSL(HSLColor(hue, 1f, .5f))))

    SliderContainer(
        sliderBrush = Brush.linearGradient(
            sliderMainColor,
        ),
    ) {
        Slider(
            value = value,
            valueRange = HSLRanges.SAT_RANGE,
            colors = SliderDefaults.colors(
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
                thumbColor = Color.Black,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,

            ),

            onValueChange = { onValueChange(it) },
        )
    }
}

/**
 * Container of HSV sliders by allowing the slider to be on top of a brush.
 *
 * @param sliderBrush: custom brush gradient representing the slider track.
 * @param sliderFunc: slider composable function.
 */
@Composable
private fun SliderContainer(sliderBrush: Brush, sliderFunc: @Composable () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(
                    brush = sliderBrush,
                    shape = MaterialTheme.shapes.medium,
                ),
        )
        sliderFunc()
    }
}

@Preview
@Composable
private fun SliderPrev() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column {
                HueSlider(value = .5f, onValueChange = {})
                LightnessSlider(hue = 150f, lightness = .5f, onValueChange = {})
                SaturationSlider(hue = 179f, value = .5f, onValueChange = {})
            }
        }
    }
}
