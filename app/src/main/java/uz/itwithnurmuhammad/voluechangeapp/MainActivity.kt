package uz.itwithnurmuhammad.voluechangeapp

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uz.itwithnurmuhammad.voluechangeapp.data.VolumeChangeReceiver
import uz.itwithnurmuhammad.voluechangeapp.presentation.HomeScreen
import uz.itwithnurmuhammad.voluechangeapp.ui.theme.VolueChangeAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var volumeReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        volumeReceiver = VolumeChangeReceiver()
        val filter = IntentFilter().apply {
            addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
            addAction("android.media.VOLUME_CHANGED_ACTION")
        }
        registerReceiver(volumeReceiver, filter)

        enableEdgeToEdge()
        setContent {
            VolueChangeAppTheme {
                HomeScreen()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(volumeReceiver)
    }
}