package com.example.android65_project

sealed class HomeScreenItem {
    data class Header(val title: String, val showSeeMore: Boolean = false) : HomeScreenItem()
    data class HorizontalMovieList(val movies: List<movie>) : HomeScreenItem()
    data class MovieItem(val movie: movie) : HomeScreenItem()
}