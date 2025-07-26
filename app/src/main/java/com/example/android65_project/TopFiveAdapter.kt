package com.example.android65_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TopFiveAdapter(private val movies: List<movie>) :
    RecyclerView.Adapter<TopFiveAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.movie_poster)
        val title: TextView = view.findViewById(R.id.movie_title)
        val rating: TextView = view.findViewById(R.id.movie_rating) // Hãy thêm ID này vào XML nếu chưa có
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_top_five, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.rating.text = "${movie.rating} ★"

        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .into(holder.poster)
    }

    override fun getItemCount() = movies.size
}