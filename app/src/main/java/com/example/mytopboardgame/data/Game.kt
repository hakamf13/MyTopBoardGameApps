package com.example.mytopboardgame.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val gameName: String,
    val gameDescription: String,
    val gameCover: Int,
    val gamePublisher: String,
    val gameReleased: String,
    val gameComplexity: String
) : Parcelable
