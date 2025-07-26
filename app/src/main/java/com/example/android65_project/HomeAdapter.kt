package com.example.android65_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeAdapter(private val items: List<HomeScreenItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_HORIZONTAL_LIST = 1
        private const val TYPE_LATEST_MOVIE = 2
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.header_title)
        val seeMore: TextView = view.findViewById(R.id.header_see_more)
    }

    class HorizontalListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.horizontal_recycler_view)
    }

    class LatestMovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.movie_poster)
        val title: TextView = view.findViewById(R.id.movie_title)
        val rating: TextView = view.findViewById(R.id.movie_rating)
        val genres: TextView = view.findViewById(R.id.movie_genres)
        val description: TextView = view.findViewById(R.id.movie_description)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeScreenItem.Header -> TYPE_HEADER
            is HomeScreenItem.HorizontalMovieList -> TYPE_HORIZONTAL_LIST
            is HomeScreenItem.MovieItem -> TYPE_LATEST_MOVIE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(inflater.inflate(R.layout.item_section_header, parent, false))
            TYPE_HORIZONTAL_LIST -> HorizontalListViewHolder(inflater.inflate(R.layout.item_horizontal_list, parent, false))
            TYPE_LATEST_MOVIE -> LatestMovieViewHolder(inflater.inflate(R.layout.item_movie_latest, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentItem = items[position]) {
            is HomeScreenItem.Header -> {
                val headerHolder = holder as HeaderViewHolder
                headerHolder.title.text = currentItem.title
                headerHolder.seeMore.visibility = if (currentItem.showSeeMore) View.VISIBLE else View.GONE
            }
            is HomeScreenItem.HorizontalMovieList -> {
                val listHolder = holder as HorizontalListViewHolder
                listHolder.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = TopFiveAdapter(currentItem.movies) // Sử dụng TopFiveAdapter ở đây
                }
            }
            is HomeScreenItem.MovieItem -> {
                val movieHolder = holder as LatestMovieViewHolder
                val movie = currentItem.movie
                movieHolder.title.text = movie.title
                movieHolder.rating.text = "${movie.rating} ★"
                movieHolder.genres.text = movie.genres
                movieHolder.description.text = movie.description
                Glide.with(holder.itemView.context).load(movie.posterUrl).into(movieHolder.poster)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
