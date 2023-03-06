package com.example.mytopboardgame.ui.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytopboardgame.R
import com.example.mytopboardgame.adapter.ListGameAdapter
import com.example.mytopboardgame.data.Game
import com.example.mytopboardgame.databinding.ActivityMainBinding
import com.example.mytopboardgame.ui.about.AboutActivity
import com.example.mytopboardgame.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var rvGame: RecyclerView
    private val listGame = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        rvGame = findViewById(R.id.rv_games)
        rvGame.setHasFixedSize(true)

        listGame.addAll(getListGame)
        showRecylerList()
    }

    private val getListGame: ArrayList<Game>
        get() {
            val dataGameName = resources.getStringArray(R.array.game_name)
            val dataGameDescription = resources.getStringArray(R.array.game_description)
            val dataGameCover = resources.obtainTypedArray(R.array.game_cover)
            val dataGamePublisher = resources.getStringArray(R.array.game_publisher)
            val dataGameReleased = resources.getStringArray(R.array.game_released)
            val dataGameComplexity = resources.getStringArray(R.array.game_complexity)
            val getListGame = ArrayList<Game>()
            for (i in dataGameName.indices) {
                val gameData = Game(dataGameName[i], dataGameDescription[i], dataGameCover.getResourceId(i, -1), dataGamePublisher[i], dataGameReleased[i], dataGameComplexity[i])
                getListGame.add(gameData)
            }
            dataGameCover.recycle()
            return getListGame
        }

    private fun showRecylerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGame.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvGame.layoutManager = LinearLayoutManager(this)
        }
        val listGameAdapter = ListGameAdapter(listGame)
        rvGame.adapter = listGameAdapter

        listGameAdapter.setOnItemClickCallback(object : ListGameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                val detailGameIntent = Intent(
                    this@MainActivity,
                    DetailActivity::class.java
                )
                detailGameIntent.putExtra(DetailActivity.GAME_KEY, data)
                startActivity(detailGameIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.about_page -> {
                val aboutIntent = Intent(
                    this@MainActivity,
                    AboutActivity::class.java
                )
                startActivity(aboutIntent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}