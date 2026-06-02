package com.example.exoapplication.models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exoapplication.R

class ReelAdapter(
    private val data: MutableList<Anime>,
    private val context: Context
) : RecyclerView.Adapter<ReelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReelViewHolder {

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.reel, parent, false)

        return ReelViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ReelViewHolder,
        position: Int
    ) {

        val reel = data[position]

        holder.title.text = reel.title

        holder.bindPlayer(reel.video)
        
        // We will control play/pause from MainActivity
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onViewRecycled(holder: ReelViewHolder) {
        super.onViewRecycled(holder)
        holder.releasePlayer()
    }
}
