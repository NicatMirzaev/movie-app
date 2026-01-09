package com.example.movie_app
import com.example.movie_app.Model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") authHeader: String
    ): Call<MovieResponse>

    @GET("search/movie")
    fun searchMovies(
        @Header("Authorization") authHeader: String,
        @Query("query") query: String
    ): Call<MovieResponse>
}
