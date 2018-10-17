package com.example.breterickson.hermetictesting.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.breterickson.hermetictesting.PerActivity
import com.example.breterickson.hermetictesting.R
import com.example.breterickson.hermetictesting.data.MovieData
import com.example.breterickson.hermetictesting.data.POSER_BASE_URL
import kotlinx.android.synthetic.main.view_movie.view.*
import javax.inject.Inject

@PerActivity
class MovieListAdapter @Inject constructor() : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies = listOf<MovieData>()

    fun setResults(movies: List<MovieData>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = inflater.inflate(R.layout.view_movie, parent, false)
        return MovieViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(movie: MovieData) {
        itemView.title.text = movie.title

        Glide.with(itemView.context)
            .load("$POSER_BASE_URL${movie.posterPath}")
            .into(itemView.poster)
    }
}