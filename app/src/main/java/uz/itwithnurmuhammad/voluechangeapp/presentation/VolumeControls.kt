package uz.itwithnurmuhammad.voluechangeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Slider
import androidx.compose.ui.draw.rotate
import uz.itwithnurmuhammad.voluechangeapp.data.AudioUtils

@Composable
fun VolumeControls(
    currentVolume: Float,
    maxVolume: Int,
    onVolumeChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        VolumeSlider(
            value = currentVolume,
            maxValue = maxVolume.toFloat(),
            onValueChange = onVolumeChange
        )
    }
}

@Composable
private fun VolumeSlider(
    value: Float,
    maxValue: Float,
    onValueChange: (Float) -> Unit
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..maxValue,
        steps = 0,
        modifier = Modifier.rotate(degrees = AudioUtils.SLIDER_ROTATION_DEGREES)
    )
}