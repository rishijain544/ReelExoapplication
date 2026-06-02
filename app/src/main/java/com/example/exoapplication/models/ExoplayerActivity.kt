package com.example.exoapplication.models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.exoapplication.R

class ExoplayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.exo)

        playerView = findViewById(R.id.ev)

        player = ExoPlayer.Builder(this).build()

        playerView.player = player

        val video =
            intent.getIntExtra("video", 0)

        val mediaItem = MediaItem.fromUri(
            "android.resource://$packageName/$video",
        )

        player.setMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
