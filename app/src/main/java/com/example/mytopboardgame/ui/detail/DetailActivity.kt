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
            tvDetailGamePublisher.text = detailGameContent.gamePublisher
            tvDetailGameReleased.text = detailGameContent.gameReleased
            tvDetailGameComplexity.text = detailGameContent.gameComplexity
        }

        val gameName = "The game name is "
        val dot = ". "
        val gamePublisher = "Published by "
        val gameReleased = "Released at "
        val gameComplexity = "This game has complexity rating at "
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,
                "$gameName${detailGameContent.gameName}$dot" +
                        "$gamePublisher${detailGameContent.gamePublisher} and " +
                        "$gameReleased${detailGameContent.gameReleased}$dot" +
                        detailGameContent.gameDescription +
                        "$dot$gameComplexity${detailGameContent.gameComplexity}")
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