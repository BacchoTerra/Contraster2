package com.brunoterra.contraster2.presentation.screens.contrast

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brunoterra.contraster2.R
import com.brunoterra.contraster2.domain.model.Contrast
import com.brunoterra.contraster2.presentation.utils.Target
import com.brunoterra.contraster2.presentation.utils.components.LabeledComponent
import com.brunoterra.contraster2.ui.theme.Contraster2Theme
import com.brunoterra.contraster2.ui.theme.Purple80
import com.brunoterra.contraster2.ui.theme.defaultContrastForegroundColor
import com.brunoterra.hslmaker.ui.HueSlider
import com.brunoterra.hslmaker.ui.LightnessSlider
import com.brunoterra.hslmaker.ui.SaturationSlider

@Composable
fun ContrastSection(
    foregroundColor: Int = defaultContrastForegroundColor.toArgb(),
    contrast: Contrast = Contrast(),
    onSwitch: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodySmall,
            color = Color(foregroundColor)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.motto),
                style = MaterialTheme.typography.displayMedium,
                color = Color(foregroundColor)
            )
            Text(text = "${contrast.ratio.value}/${contrast.score}", color = Color(foregroundColor))
        }

        Text(
            text = stringResource(id = R.string.lorem_display),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(foregroundColor)
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = stringResource(id = R.string.test_palette), color = Color(foregroundColor))
            Icon(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        onSwitch()
                    },
                painter = painterResource(id = R.drawable.baseline_sync_24),
                contentDescription = stringResource(
                    id = R.string.cd_switch
                ),
                tint = Color(foregroundColor)
            )
        }

    }
}

@Composable
fun ColorHeaderSection(
    currentTarget: Target,
    hexCode: String,
    onTargetSelection: (Target) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleGroup(currentTarget, onTargetSelection)
            Text(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    )
                    .padding(6.dp),
                text = hexCode
            )
        }
    }
}

@Composable
fun ToggleGroup(currentTarget: Target, onSelection: (Target) -> Unit) {
    Row {
        OutlinedButton(
            shape = RoundedCornerShape(
                topStart = 8.dp,
                bottomStart = 8.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentTarget == Target.BACKGROUND) Purple80 else Color.White,
                contentColor = Color.Gray
            ),
            onClick = { onSelection(Target.BACKGROUND) }) {
            Text(text = stringResource(id = R.string.background))
        }

        OutlinedButton(
            shape = RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 8.dp,
                bottomEnd = 8.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentTarget == Target.FOREGROUND) Purple80 else Color.White,
                contentColor = Color.Gray
            ),
            onClick = { onSelection(Target.FOREGROUND) }
        ) {
            Text(text = stringResource(id = R.string.foreground))
        }
    }
}

@Composable
fun SlidersSection(
    hue: Float,
    sat: Float,
    lightness: Float,
    onHueChange: (Float) -> Unit,
    onSaturationChange: (Float) -> Unit,
    onLightnessChange: (Float) -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LabeledComponent(icon = R.drawable.baseline_palette_24, label = R.string.hue) {
            HueSlider(value = hue, onValueChange = {
                onHueChange(it)
            })
        }

        LabeledComponent(icon = R.drawable.baseline_light_mode_24, label = R.string.lightness) {
            LightnessSlider(
                hue = hue,
                lightness = lightness,
                onValueChange = { onLightnessChange(it) })
        }

        LabeledComponent(icon = R.drawable.baseline_opacity_24, label = R.string.saturation) {
            SaturationSlider(
                hue = hue,
                value = sat,
                onValueChange = { onSaturationChange(it) })
        }
    }
}

@Preview
@Composable
private fun ContrastSectionPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContrastSection()
        }
    }
}

@Preview
@Composable
private fun ColorHeaderSectionPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ColorHeaderSection(Target.BACKGROUND, "0xFF000000") {}
        }
    }
}

@Preview
@Composable
private fun LabeledComponentPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LabeledComponent(
                icon = R.drawable.baseline_sync_24,
                label = R.string.test_palette
            ) {
                HueSlider(value = 0f, onValueChange = {})
            }
        }
    }
}