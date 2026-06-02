package com.example.exoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.exoapplication.models.Anime
import com.example.exoapplication.models.ReelAdapter
import com.example.exoapplication.models.ReelViewHolder

class MainActivity : AppCompatActivity() {

    private var data = mutableListOf<Anime>()
    private lateinit var recyclerView: RecyclerView
    private var currentPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rec)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        data.add(Anime("RCB Match 1", R.raw.rcb1, R.raw.rcb1))
        data.add(Anime("RCB Match 2", R.raw.rcb2, R.raw.rcb2))
        data.add(Anime("RCB Match 3", R.raw.rcb3, R.raw.rcb3))
        data.add(Anime("RCB Match 4", R.raw.rcb4, R.raw.rcb4))

        recyclerView.adapter = ReelAdapter(data, this)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val view = snapHelper.findSnapView(layoutManager)
                    view?.let {
                        val position = layoutManager.getPosition(it)
                        if (position != currentPosition) {
                            playVideoAt(position)
                            currentPosition = position
                        }
                    }
                }
            }
        })

        // Initial play
        recyclerView.post {
            playVideoAt(0)
            currentPosition = 0
        }
    }

    private fun playVideoAt(position: Int) {
        for (i in 0 until recyclerView.childCount) {
            val child = recyclerView.getChildAt(i)
            val holder = recyclerView.getChildViewHolder(child) as? ReelViewHolder
            val pos = recyclerView.getChildAdapterPosition(child)
            if (pos == position) {
                holder?.play()
            } else {
                holder?.pause()
            }
        }
    }
}
