package com.example.movie_app.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.Adapter.MovieAdapter
import com.example.movie_app.Database.MovieDatabase
import com.example.movie_app.Model.MovieResponse
import com.example.movie_app.R
import com.example.movie_app.RetrofitInstance
import com.example.movie_app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NDhiNzFiMmEwZmNjNjQ4ZTI2ZWNlYTUzNDc5OGQyMSIsIm5iZiI6MTc2NzU5NDA2OC40Nywic3ViIjoiNjk1YjU4NTQyZWFjODcyNTc2ZTQxOTExIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.wV8KaRD4T_qpzIq_FBySIfcL7wTevrT6iTAP3w0OBJY"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        RetrofitInstance.api.getPopularMovies(API_KEY)
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val movies = response.body()?.results ?: emptyList()

                        binding.recyclerViewMovies.adapter =
                            MovieAdapter(movies) { movie ->
                                lifecycleScope.launch {
                                    MovieDatabase.getDatabase(requireContext())
                                        .movieDao().insertMovie(movie)
                                    Toast.makeText(requireContext(), "${movie.title} added to favourites!", Toast.LENGTH_SHORT).show()
                                }
                            }

                        binding.recyclerViewMovies.layoutManager =
                            LinearLayoutManager(requireContext())
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}