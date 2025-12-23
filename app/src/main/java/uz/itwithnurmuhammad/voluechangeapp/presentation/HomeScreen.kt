package uz.itwithnurmuhammad.voluechangeapp.presentation

import android.content.Context
import android.media.AudioManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.flow.collectLatest
import uz.itwithnurmuhammad.voluechangeapp.data.AudioUtils
import uz.itwithnurmuhammad.voluechangeapp.data.VolumeChangeFlow

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val audioManager = remember { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    val maxVolume = remember { audioManager.getStreamMaxVolume(AudioUtils.STREAM_TYPE) }

    var currentVolume by rememberSaveable {
        mutableFloatStateOf(audioManager.getStreamVolume(AudioUtils.STREAM_TYPE).toFloat())
    }

    LaunchedEffect(lifecycleOwner) {
            VolumeChangeFlow.volumeChanged.collectLatest {
                val newVolume = audioManager.getStreamVolume(AudioUtils.STREAM_TYPE).toFloat()
                if (newVolume != currentVolume) {
                    currentVolume = newVolume
                }
            }
    }

    Scaffold { innerPadding ->
        VolumeControls(
            currentVolume = currentVolume,
            maxVolume = maxVolume,
            onVolumeChange = { newVolume ->
                if (currentVolume != newVolume) {
                    currentVolume = newVolume
                    AudioUtils.updateMediaVolumeSafely(audioManager, newVolume)
                }
            },
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        )
    }
}