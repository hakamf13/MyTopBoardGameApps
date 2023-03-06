package com.example.mytopboardgame.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bumptech.glide.Glide
import com.example.mytopboardgame.R
import com.example.mytopboardgame.data.Game
import com.example.mytopboardgame.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private val detailBinding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailBinding.root)

        supportActionBar?.hide()

        val detailGameContent = intent.getParcelableExtra<Game>(GAME_KEY) as Game

        detailBinding.apply {
            Glide.with(this@DetailActivity)
                .load(detailGameContent.gameCover)
                .into(ivDetailGameCover)
            tvDetailGameName.text = detailGameContent.gameName
            tvDetailGameDescription.text = detailGameContent.gameDescription
        }

        val frontText = "The game name is "
        val separator = ". "
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$frontText${detailGameContent.gameName}$separator${detailGameContent.gameDescription}")
            type = "text/plain"
        }

        val shareButton = findViewById<Button>(R.id.action_share)
        shareButton.setOnClickListener {
            val shareIntent = Intent.createChooser(sendIntent, "Share")
            startActivity(shareIntent)
        }
    }

    companion object {
        const val GAME_KEY = "game_key"
    }
}