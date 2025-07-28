package com.example.android65_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        val mainRecyclerView: RecyclerView = findViewById(R.id.main_recycler_view)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)

        val homeScreenItems = createDummyData()
        mainRecyclerView.adapter = HomeAdapter(homeScreenItems)
    }

    private fun setupBottomNavigation() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> true
                R.id.nav_search -> {
                    Toast.makeText(this, "Search Clicked!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_bookmark -> {
                    handleBookmarkClick()
                    true
                }
                else -> false
            }
        }
    }

    private fun handleBookmarkClick() {
        if (sessionManager.isLoggedIn()) {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }



    private fun createDummyData(): List<HomeScreenItem> {
        val topFiveMovies = listOf(
            movie(1, "Hitman's Wife's Bodyguard", "https://image.tmdb.org/t/p/w500/2uIuoz1Y8i2nKLS2r5agNb8z2Ro.jpg", 3.5f, "", ""),
            movie(2, "Jungle Cruise", "https://image.tmdb.org/t/p/w500/9dKCd55IuTT5Q0B7KynZloCjSgH.jpg", 4.0f, "", ""),
            movie(3, "Black Widow", "https://image.tmdb.org/t/p/w500/qAZ0pzat24kLdO3o8e8OKmwXLb1.jpg", 3.8f, "", ""),
            movie(4, "F9", "https://image.tmdb.org/t/p/w500/bOFaAXmWWXC3Rbv4u4uM9Z8tdp.jpg", 3.7f, "", ""),
            movie(5, "The Tomorrow War", "https://image.tmdb.org/t/p/w500/34nDCQZwaEvsy4CFO5hkGRFDCVU.jpg", 4.2f, "", "")
        )

        val latestMovies = listOf(
            movie(1, "Hitman's Wife's Bodyguard", "https://image.tmdb.org/t/p/w500/2uIuoz1Y8i2nKLS2r5agNb8z2Ro.jpg", 3.5f, "Action, Comedy, Crime", "The world's most lethal odd couple - bodyguard Michael Bryce and hitman Darius Kincaid - are back on another life-threatening mission."),
            movie(6, "Space Jam: A New Legacy", "https://image.tmdb.org/t/p/w500/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg", 4.1f, "Animation, Comedy, Family", "Superstar LeBron James and his young son Dom are trapped in a digital space by a rogue A.I.")
        )

        val items = mutableListOf<HomeScreenItem>()
        items.add(HomeScreenItem.Header("Top Five."))
        items.add(HomeScreenItem.HorizontalMovieList(topFiveMovies))
        items.add(HomeScreenItem.Header("Latest.", showSeeMore = true))
        latestMovies.forEach { movie ->
            items.add(HomeScreenItem.MovieItem(movie))
        }

        return items
    }
}