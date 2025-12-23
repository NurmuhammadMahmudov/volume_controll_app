package uz.itwithnurmuhammad.voluechangeapp.data

import android.media.AudioManager

object AudioUtils {
    const val STREAM_TYPE = AudioManager.STREAM_MUSIC
    const val SLIDER_ROTATION_DEGREES = -90f

    fun updateMediaVolumeSafely(audioManager: AudioManager, volume: Float) {
        val maxVolume = audioManager.getStreamMaxVolume(STREAM_TYPE)
        val safeVolume = volume.toInt().coerceIn(0, maxVolume)
        audioManager.setStreamVolume(STREAM_TYPE, safeVolume, 0)
    }
}