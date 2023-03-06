package com.example.mytopboardgame.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mytopboardgame.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.hide()

        val imageView = findViewById<ImageView>(R.id.civ_about_my_photo)
        imageView.setImageResource(R.drawable.img_profile)
    }
}