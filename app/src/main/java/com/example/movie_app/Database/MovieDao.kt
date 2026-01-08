package com.example.movie_app.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie_app.Model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)
}