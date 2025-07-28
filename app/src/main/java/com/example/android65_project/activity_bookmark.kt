package com.example.android65_project

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookmarkActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        sessionManager = SessionManager(this)

        // Ánh xạ các view từ layout
        val backButton: ImageView = findViewById(R.id.image_view_back)
        val logoutButton: ImageView = findViewById(R.id.image_view_logout)
        val bookmarksRecyclerView: RecyclerView = findViewById(R.id.recycler_view_bookmarks)

        // Cài đặt RecyclerView
        bookmarksRecyclerView.layoutManager = LinearLayoutManager(this)

        // TODO: Thay thế bằng dữ liệu bookmark thực tế từ database hoặc API
        val dummyBookmarks = createDummyBookmarks()
        bookmarksRecyclerView.adapter = BookmarkAdapter(dummyBookmarks)

        // Xử lý sự kiện click
        backButton.setOnClickListener {
            finish() // Đóng màn hình hiện tại và quay lại màn hình trước đó
        }

        logoutButton.setOnClickListener {
            handleLogout()
        }
    }

    private fun handleLogout() {
        // 1. Xóa trạng thái đăng nhập
        sessionManager.setLogin(false)

        // 2. Tạo Intent để quay về MainActivity
        val intent = Intent(this, MainActivity::class.java)

        // 3. Quan trọng: Xóa toàn bộ activity stack cũ
        // Điều này ngăn người dùng bấm nút Back để quay lại trang Bookmark sau khi đã logout
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)

        // Hiển thị thông báo cho người dùng
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }

    // Hàm tạo dữ liệu giả
    private fun createDummyBookmarks(): List<movie> {
        return listOf(
            movie(1, "Hitman's Wife's Bodyguard", "https://image.tmdb.org/t/p/w500/2uIuoz1Y8i2nKLS2r5agNb8z2Ro.jpg", 3.5f, "Action, Comedy, Crime", "The world's most lethal odd couple - bodyguard Michael Bryce and hitman Darius Kincaid - are back..."),
            movie(6, "Space Jam: A New Legacy", "https://image.tmdb.org/t/p/w500/5bFK5d3mVTAvBCXi5NPWH0tYjKl.jpg", 4.1f, "Animation, Comedy, Family", "Superstar LeBron James and his young son Dom are trapped in a digital space by a rogue A.I..."),
            movie(3, "Black Widow", "https://image.tmdb.org/t/p/w500/qAZ0pzat24kLdO3o8e8OKmwXLb1.jpg", 3.8f, "Action, Sci-Fi", "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.")
        )
    }
}