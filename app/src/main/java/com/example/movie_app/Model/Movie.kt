package com.example.movie_app.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double
)