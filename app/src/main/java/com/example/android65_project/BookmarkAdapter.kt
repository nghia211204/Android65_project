package com.example.android65_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BookmarkAdapter(private val bookmarkedMovies: List<movie>) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    class BookmarkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.movie_poster)
        val title: TextView = view.findViewById(R.id.movie_title)
        val rating: TextView = view.findViewById(R.id.movie_rating)
        val genres: TextView = view.findViewById(R.id.movie_genres)
        val description: TextView = view.findViewById(R.id.movie_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_latest, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val movie = bookmarkedMovies[position]

        holder.title.text = movie.title
        holder.rating.text = "${movie.rating} ★"
        holder.genres.text = movie.genres
        holder.description.text = movie.description

        Glide.with(holder.itemView.context)
            .load(movie.posterUrl)
            .into(holder.poster)
    }

    override fun getItemCount() = bookmarkedMovies.size
}
