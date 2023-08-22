package com.brunoterra.contraster2.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.brunoterra.contraster2.ui.theme.Contraster2Theme
import com.brunoterra.contraster2.ui.theme.defaultContrastBackgroundColor
import com.brunoterra.contraster2.ui.theme.defaultContrastForegroundColor
import com.brunoterra.hsvmaker.ui.HueSlider
import com.brunoterra.hsvmaker.ui.SaturationSlider
import com.brunoterra.hsvmaker.ui.ValueSlider

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(defaultContrastBackgroundColor)
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical
            )
    ) {

        Column {
            ContrastSection()

            Column(
                Modifier
                    .background(
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                        color = MaterialTheme.colorScheme.background
                    )
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ColorHeaderSection()
                Spacer(modifier = Modifier.height(24.dp))
                SlidersSection()
            }

        }

    }
}

@Composable
private fun ContrastSection(
    foregroundColor: Int = defaultContrastForegroundColor.toArgb()
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
            Text(text = "Contrast ratio", color = Color(foregroundColor))
        }

        Text(
            text = stringResource(id = R.string.lorem_display),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(foregroundColor)
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = stringResource(id = R.string.test_palette), color = Color(foregroundColor))
            Icon(
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
private fun ColorHeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleGroup()
            Text(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    )
                    .padding(6.dp),
                text = "hex color"
            )
        }
    }
}

@Composable
private fun ToggleGroup() {
    Row {
        OutlinedButton(
            shape = RoundedCornerShape(
                topStart = 8.dp,
                bottomStart = 8.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            onClick = {}) {
            Text(text = stringResource(id = R.string.background))
        }

        OutlinedButton(shape = RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 0.dp,
            topEnd = 8.dp,
            bottomEnd = 8.dp
        ), onClick = {}) {
            Text(text = stringResource(id = R.string.foreground))
        }
    }
}

@Composable
fun SlidersSection() {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        LabeledComponent(icon = R.drawable.baseline_palette_24, label = R.string.hue) {
            HueSlider(value = 0f, onValueChange = {})
        }

        LabeledComponent(icon = R.drawable.baseline_light_mode_24, label = R.string.value) {
            ValueSlider(hue = 160f, value = 0f, onValueChange = {})
        }

        LabeledComponent(icon = R.drawable.baseline_opacity_24, label = R.string.saturation) {
            SaturationSlider(hue = 160f, value = 0f, onValueChange = {})
        }
    }
}

@Composable
fun LabeledComponent(@DrawableRes icon: Int, @StringRes label: Int, func: @Composable () -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = stringResource(id = label), color = MaterialTheme.colorScheme.onBackground)
        }
        func()
    }
}

@Preview
@Composable
fun MainScreenLightPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScreenDarkPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun ContrastSectionPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ContrastSection()
        }
    }
}

@Preview
@Composable
fun ColorHeaderSectionPrev() {
    Contraster2Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ColorHeaderSection()
        }
    }
}

@Preview
@Composable
fun LabeledComponentPrev() {
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