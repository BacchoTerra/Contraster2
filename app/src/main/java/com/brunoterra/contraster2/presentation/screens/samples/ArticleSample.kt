package com.brunoterra.contraster2.presentation.screens.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.brunoterra.contraster2.R

@Composable
fun ArticleScreen(
    backgroundColor: Int,
    foregroundColor: Int,
    score: Double,
    onNavigateBack: () -> Unit
) {
    SampleContainer(
        topBarTitle = stringResource(id = R.string.contrast_score, score),
        onNavigateBack = { onNavigateBack() }) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(color = Color(backgroundColor))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.lorem_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(foregroundColor),
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .border(1.dp, color = Color(foregroundColor), shape = CircleShape)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = stringResource(R.string.cd_person_icon)
                )

                Text(
                    text = stringResource(id = R.string.lorem_author),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(foregroundColor)
                )
            }

            Text(
                text = stringResource(id = R.string.loren_article),
                color = Color(foregroundColor)
            )

        }
    }
}