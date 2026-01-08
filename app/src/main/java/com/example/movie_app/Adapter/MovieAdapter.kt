package com.example.movie_app.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.Model.Movie
import com.example.movie_app.databinding.ItemMovieBinding

class MovieAdapter(
    private val movies: List<Movie>,
    private val onFavouriteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: ItemMovieBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.binding.tvTitle.text = movie.title
        holder.binding.tvRating.text = "⭐ ${movie.vote_average}"
        holder.binding.tvOverview.text = movie.overview

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(holder.binding.imgPoster)

        holder.binding.btnAddFavourite.setOnClickListener {
            onFavouriteClick(movie)
        }
    }

    override fun getItemCount() = movies.size
}