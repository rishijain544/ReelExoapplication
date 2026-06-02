package com.example.exoapplication.models

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.example.exoapplication.R

class ReelViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var playerView: PlayerView =
        itemView.findViewById(R.id.pv)

    var title: TextView =
        itemView.findViewById(R.id.title)

    var ll: RelativeLayout =
        itemView.findViewById(R.id.layout)

    private var player: ExoPlayer? = null

    fun bindPlayer(video: Int) {
        if (player == null) {
            player = ExoPlayer.Builder(itemView.context).build()
            playerView.player = player
            player?.repeatMode = Player.REPEAT_MODE_ONE
        }

        val mediaItem = MediaItem.fromUri(
            "android.resource://${itemView.context.packageName}/$video",
        )

        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    fun play() {
        player?.playWhenReady = true
    }

    fun pause() {
        player?.playWhenReady = false
    }

    fun releasePlayer() {
        player?.release()
        player = null
    }
}
