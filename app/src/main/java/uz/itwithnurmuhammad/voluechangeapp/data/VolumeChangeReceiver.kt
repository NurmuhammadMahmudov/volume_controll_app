package uz.itwithnurmuhammad.voluechangeapp.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object VolumeChangeFlow {
    private val _volumeChanged = MutableSharedFlow<Unit>(replay = 0)
    val volumeChanged = _volumeChanged.asSharedFlow()

    fun emitChange() {
        _volumeChanged.tryEmit(value = Unit)
    }
}
class VolumeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AudioManager.ACTION_AUDIO_BECOMING_NOISY,
            "android.media.VOLUME_CHANGED_ACTION",
            AudioManager.RINGER_MODE_CHANGED_ACTION -> {
                VolumeChangeFlow.emitChange()
            }
        }
    }
}