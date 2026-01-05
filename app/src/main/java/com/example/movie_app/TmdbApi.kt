package com.example.movie_app
import com.example.movie_app.Model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") authHeader: String
    ): Call<MovieResponse>
}
