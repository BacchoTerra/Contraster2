package com.brunoterra.contraster2.presentation.screens.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SampleContainer(topBarTitle: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            SampleToolbar(topBarTitle)
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleToolbar(title: String) {
    TopAppBar(title = { Text(text = title) })
}

@Preview
@Composable
private fun SampleToolbarPrev() {
    SampleToolbar("Title")
}