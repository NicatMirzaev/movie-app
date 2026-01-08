package com.example.movie_app.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.Adapter.MovieAdapter
import com.example.movie_app.Database.MovieDatabase
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.launch

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        setupRecyclerView()
        observeFavourites()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(emptyList(), "Remove") { movie ->
            lifecycleScope.launch {
                MovieDatabase.getDatabase(requireContext()).movieDao().deleteMovie(movie.id)
                Toast.makeText(requireContext(), "${movie.title} removed from favourites", Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerViewFavourites.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeFavourites() {
        lifecycleScope.launch {
            MovieDatabase.getDatabase(requireContext()).movieDao().getAllMovies().collect { movies ->
                if (movies.isEmpty()) {
                    binding.tvEmptyMessage.visibility = View.VISIBLE
                    binding.recyclerViewFavourites.visibility = View.GONE
                } else {
                    binding.tvEmptyMessage.visibility = View.GONE
                    binding.recyclerViewFavourites.visibility = View.VISIBLE
                    movieAdapter.updateMovies(movies)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
